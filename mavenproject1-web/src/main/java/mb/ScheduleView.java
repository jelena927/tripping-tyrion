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
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Konsultacije;
import model.Predmet;
import model.Profesor;
import model.Termin;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.UploadedFile;

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
    @ManagedProperty("#{mBLogovanje}")
    private MBLogovanje mBLogovanje;
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private DefaultScheduleEvent event = new DefaultScheduleEvent();
    private String txt1="text";
    private String console;
    private Predmet predmet;
    private Profesor profesor;
    private String termini;
    private Termin termin;
    private Date selektovaniDatum;

    public MBLogovanje getmBLogovanje() {
        return mBLogovanje;
    }

    public void setmBLogovanje(MBLogovanje mBLogovanje) {
        this.mBLogovanje = mBLogovanje;
    }

    public Date getSelektovaniDatum() {
        return selektovaniDatum;
    }

    public void setSelektovaniDatum(Date danasnjiDatum) {
        this.selektovaniDatum = danasnjiDatum;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public String getTermini() {
        return termini;
    }

    public void setTermini(String termini) {
        this.termini = termini;
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

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }
    
    private String txt2;

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }
 
    public MBProfesor getMbProfesor() {
        return mbProfesor;
    }

    public void setMbProfesor(MBProfesor mbProfesor) {
        this.mbProfesor = mbProfesor;
    }
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        selektovaniDatum = fourDaysLater3pm();
        termin = new Termin();
//        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am(), true));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay10Am()));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay10Am(), nextDay11Am()));
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
//         
//        lazyEventModel = new LazyScheduleModel(){
//             
//            @Override
//            public void loadEvents(Date start, Date end) {
//                Date random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
//                 
//                random = getRandomDate(start);
//                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
//            }   
//        };
    }
     
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
         
        return date.getTime();
    }
    
    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone(); 
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);
         
        return t.getTime();
    }
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
         
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
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
     
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null){
            eventModel.addEvent(event);
            System.out.println("+++++++++++---------------if");
        } else {
            eventModel.updateEvent(event);
            System.out.println("+++++++++++---------------else");
        }
        event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        System.out.println("-------------event selected");
        event = (DefaultScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        System.out.println("-------------date selected");
        selektovaniDatum = ((Date) selectEvent.getObject());
        event = new DefaultScheduleEvent("Zauzeto", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        event.setEditable(true);
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
    
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
    
    public List<String> completeText2(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
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
        predmet = null;
        profesor = null;
        
    }
    
    public void onProfesorChange(){
        termini = "";
        eventModel.clear();
        for (Konsultacije k : profesor.getKonsultacijeList()) {
            SimpleDateFormat sdfPocetak = new SimpleDateFormat("dd.MM. hh:mm");
            SimpleDateFormat sdfKraj = new SimpleDateFormat("hh:mm");
            if(k.getPredmetId().getPredmetId().equals(predmet.getPredmetId())){
                termini += sdfPocetak.format(k.getVremePocetka()) + " - " + 
                        sdfKraj.format(k.getVremeZavrsetka()) + "\n";
                for (Termin t: k.getTerminList()) {
                    eventModel.addEvent(new DefaultScheduleEvent("Zauzeto", 
                            t.getVreme(), new Date(t.getVreme().getTime() + k.getTrajanjeJednogTermina()*60*1000)));
                }
            }
        }
    }
    
    public void onPredmetChange(){
        profesor = null;
        eventModel.clear();
    }

}
