/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Konsultacije;
import model.Predmet;
import model.Profesor;
import model.Student;
import model.Termin;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author admin
 */
@SessionScoped
@ManagedBean(name = "scheduleView")
public class ScheduleView implements Serializable {
    @ManagedProperty("#{mbPredmet}")
    private MBPredmet mbPredmet;
    @ManagedProperty("#{mbProfesor}")
    private MBProfesor mbProfesor;
    @ManagedProperty("#{mbTermin}")
    private MbTermin mbTermin;
    @ManagedProperty("#{mbKonsultacije}")
    private MbKonsultacije mbKonsultacije;
    @ManagedProperty("#{mbKorisnik}")
    private MbKorisnik mbKorisnik;
    private ScheduleModel eventModel;
    private DefaultScheduleEvent event = new DefaultScheduleEvent();
    private Predmet predmet;
    private Profesor profesor;
    private Date selektovaniDatum;
    private String tema;
    private Date vreme;
    private boolean disableProfesore = true;
    private Konsultacije konsultacije;
    private Student student;
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        selektovaniDatum = new Date(today().getTimeInMillis());
        student = (Student) mbKorisnik.vratiKorisnika();
    }

    public Date getSelektovaniDatum() {
        return selektovaniDatum;
    }

    public void setSelektovaniDatum(Date danasnjiDatum) {
        this.selektovaniDatum = danasnjiDatum;
    }
    
    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public MBProfesor getMbProfesor() {
        return mbProfesor;
    }

    public void setMbProfesor(MBProfesor mbProfesor) {
        this.mbProfesor = mbProfesor;
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
 
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }
     
    public DefaultScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent(ActionEvent actionEvent) throws Exception {
        if(predmet == null || profesor == null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                    "Morate izabrati profesora i predmet.");
            FacesContext.getCurrentInstance().addMessage("greske", message);
            return;
        }
            
        if(tema == null || vreme == null || selektovaniDatum == null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                    "Sva polja su obavezna.");
            FacesContext.getCurrentInstance().addMessage("greske", message);
            return;
        }
        event.setTitle(tema);
        Calendar start = Calendar.getInstance();
        start.set(selektovaniDatum.getYear()+1900, selektovaniDatum.getMonth(), selektovaniDatum.getDate(), 
                                vreme.getHours(), vreme.getMinutes());
        Calendar end = (Calendar) start.clone();
        end.add(Calendar.MINUTE, 30);
        if(!checkTermin(new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()))){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", 
                    "Izabrani datum nije dostupan.");
            FacesContext.getCurrentInstance().addMessage("greske", message);
            return;
        }
        event.setStartDate(new Date(start.getTimeInMillis()));
        event.setEndDate(new Date(end.getTimeInMillis()));
        
        mbTermin.dodaj(event, konsultacije, student, profesor);
        
        eventModel.addEvent(event);
        reset();
        RequestContext.getCurrentInstance().execute("eventDialog.hide()");
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        selektovaniDatum = ((Date) selectEvent.getObject());
        event = new DefaultScheduleEvent(tema, (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Predmet> vratiPredmete(String prefix) {
        return mbPredmet.vratiSvePredmete(prefix);
    }

    public MBPredmet getMbPredmet() {
        return mbPredmet;
    }

    public void setMbPredmet(MBPredmet mbPredmet) {
        this.mbPredmet = mbPredmet;
    }
    
    public List<Profesor> vratiProfesore(String prefix){
        if(predmet == null)
            return mbProfesor.vratiSveProfesore();
        List<Profesor> profesori = predmet.getProfesorList();
        if(prefix == null)
            return profesori;
        List<Profesor> filtrirani = new ArrayList<Profesor>();
        for (Profesor profesor1 : profesori) {
            if(profesor1.getIme().startsWith(prefix) || profesor1.getPrezime().startsWith(prefix))
                filtrirani.add(profesor1);
        }
        return filtrirani;
    }
    
    public void reset(){
        vreme = null;
        selektovaniDatum = null;
        tema = null;
        event = null;
    }
    
    public void onProfesorChange(){
        eventModel.clear();
        for (Konsultacije k : profesor.getKonsultacijeList()) {
            if(k.getPredmetId().equals(predmet)){
                for (Termin t: k.getTerminList()) {
                    eventModel.addEvent(new DefaultScheduleEvent(
                            (student.equals(t.getStudent()))?t.getTema():"Zauzeto", 
                            t.getTerminPK().getVreme(), 
                            new Date(t.getTerminPK().getVreme().getTime() + k.getTrajanjeJednogTermina()*60*1000)));
                }
            }
        }
    }
    
    private boolean checkTermin(Date start, Date end){
        for (Konsultacije k : profesor.getKonsultacijeList()) {
            if(k.getPredmetId().equals(predmet)){
                if(k.getVremePocetka().before(start) && k.getVremeZavrsetka().after(end)){
                    for(Termin t: k.getTerminList()){
                        if(t.getTerminPK().getVreme().after(start) && t.getTerminPK().getVreme().before(end))
                            return false;
                    }
                konsultacije = k;
                return true;
                }
            }
        }
        return false;
    }
    
    public void onPredmetChange(){
        profesor = null;
        eventModel.clear();
        disableProfesore = false;
    }
    
    public void onPredmetType(){
        predmet = null;
        profesor = null;
        eventModel.clear();
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public boolean isDisableProfesore() {
        return disableProfesore;
    }

    public void setDisableProfesore(boolean disableProfesore) {
        this.disableProfesore = disableProfesore;
    }

    public MbTermin getMbTermin() {
        return mbTermin;
    }

    public void setMbTermin(MbTermin mbTermin) {
        this.mbTermin = mbTermin;
    }

    public MbKorisnik getMbKorisnik() {
        return mbKorisnik;
    }

    public void setMbKorisnik(MbKorisnik mbKorisnik) {
        this.mbKorisnik = mbKorisnik;
    }
    
    public String formatDatum(Konsultacije k){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.");
        return sdf.format(k.getVremePocetka());
    }
    
    public String formatVreme(Konsultacije k){
        String sVreme = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sVreme += sdf.format(k.getVremePocetka());
        sVreme += " - ";
        sVreme += sdf.format(k.getVremeZavrsetka());
        return sVreme;
    }

    public MbKonsultacije getMbKonsultacije() {
        return mbKonsultacije;
    }

    public void setMbKonsultacije(MbKonsultacije mbKonsultacije) {
        this.mbKonsultacije = mbKonsultacije;
    }

}
