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
@ManagedBean(name = "scheduleViewProf")
public class ScheduleViewProfesor implements Serializable {
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
    private Predmet predmet;
    private Profesor profesor;
    private Date selektovaniDatum;
    private boolean disableDodaj = true;
    private Date kDatum;
    private Date kOd;
    private Date kDo;
    private int trajanje = 15;
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        selektovaniDatum = new Date(today().getTimeInMillis());
        profesor = (Profesor) mbKorisnik.vratiKorisnika();
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
     
    public void onEventSelect(SelectEvent selectEvent) {
//        event = (DefaultScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
//        selektovaniDatum = ((Date) selectEvent.getObject());
//        event = new DefaultScheduleEvent(tema, (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
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
    
    public List<Predmet> vratiPredmeteZaProfesora(String prefix) {
        List<Predmet> lista = new ArrayList<Predmet>();
        lista.addAll(profesor.getPredmetList());
        
        if(prefix.isEmpty())
            return lista;
        List<Predmet> lista2 = new ArrayList<Predmet>();
        for (Predmet predmet1 : lista) {
            if(predmet1.getNaziv().toLowerCase().startsWith(prefix.toLowerCase()))
                lista2.add(predmet1);
        }
        return lista2;
    }
    
    public List<Konsultacije> konsultacijeZaPredmet(){
        List<Konsultacije> list = new ArrayList<Konsultacije>();
        if(predmet == null)
            return list;
        for (Konsultacije konsultacije : profesor.getKonsultacijeList()) {
            if(konsultacije.getPredmetId().equals(predmet))
                list.add(konsultacije);
        }
        return list;
    }

    public MBPredmet getMbPredmet() {
        return mbPredmet;
    }

    public void setMbPredmet(MBPredmet mbPredmet) {
        this.mbPredmet = mbPredmet;
    }
    
    public void onPredmetChange(){
        eventModel.clear();
        disableDodaj = false;
        for (Konsultacije k : profesor.getKonsultacijeList()) {
            if(k.getPredmetId().equals(predmet)){
                for (Termin t: k.getTerminList()) {
                    eventModel.addEvent(new DefaultScheduleEvent(t.getTema(), 
                            t.getTerminPK().getVreme(), 
                            new Date(t.getTerminPK().getVreme().getTime() + k.getTrajanjeJednogTermina()*60*1000)));
                }
            }
        }
    }
    
    public void onPredmetType(){
        predmet = null;
        disableDodaj = true;
        eventModel.clear();
    }

    public boolean isDisableDodaj() {
        return disableDodaj;
    }

    public void setDisableDodaj(boolean disableDodaj) {
        this.disableDodaj = disableDodaj;
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
    
    public void otkazi(Konsultacije k){
        mbKonsultacije.obrisiKonsultacije(k);
        profesor = (Profesor) mbKorisnik.vratiKorisnika();
    }
    
    public void dodajKonsultacije(){
        Konsultacije k = new Konsultacije();
        k.setPredmetId(predmet);
        k.setTrajanjeJednogTermina(trajanje);
        Calendar start = Calendar.getInstance();
        start.set(trajanje, trajanje, trajanje, trajanje, trajanje, trajanje);
        start.set(kDatum.getYear()+1900, kDatum.getMonth(), kDatum.getDate(), 
                                kOd.getHours(), kOd.getMinutes());
        k.setVremePocetka(new Date(start.getTimeInMillis()));
        Calendar end = (Calendar) start.clone();
        end.set(Calendar.HOUR, kDo.getHours());
        end.set(Calendar.MINUTE, kDo.getMinutes());
        k.setVremeZavrsetka(new Date(end.getTimeInMillis()));
        mbKonsultacije.dodajKonsultacije(k);
        RequestContext.getCurrentInstance().execute("konsultacijeDialog.hide()");
        profesor = (Profesor) mbKorisnik.vratiKorisnika();
    }
    
    public Date getkDatum() {
        return kDatum;
    }

    public void setkDatum(Date kDatum) {
        this.kDatum = kDatum;
    }

    public Date getkOd() {
        return kOd;
    }

    public void setkOd(Date kOd) {
        this.kOd = kOd;
    }

    public Date getkDo() {
        return kDo;
    }

    public void setkDo(Date kDo) {
        this.kDo = kDo;
    }

    public MbKonsultacije getMbKonsultacije() {
        return mbKonsultacije;
    }

    public void setMbKonsultacije(MbKonsultacije mbKonsultacije) {
        this.mbKonsultacije = mbKonsultacije;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
}
