/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.SBTerminLocal;
import java.io.Serializable;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Konsultacije;
import model.Profesor;
import model.Student;
import model.Termin;
import model.TerminPK;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author admin
 */
@ManagedBean(name = "mbTermin")
@SessionScoped
public class MbTermin implements Serializable{
    @EJB
    private SBTerminLocal sBTermin;
    @ManagedProperty("#{mbKorisnik}")
    private MbKorisnik mbKorisnik;

    /**
     * Creates a new instance of MbTermin
     */
    public MbTermin() {
    }
    
    public void dodaj(DefaultScheduleEvent e, Konsultacije k, Student s, Profesor p){
        
        Termin t = new Termin();
        t.setTema(e.getTitle());
        t.setStudent(s);
        TerminPK tpk = new TerminPK();
        tpk.setVreme(e.getStartDate());
        tpk.setKonsultacije(k);
        t.setTerminPK(tpk);
        
        sBTermin.sacuvajTermin(t);
        posaljiMail(s, p, t);
    }

    public MbKorisnik getMbKorisnik() {
        return mbKorisnik;
    }

    public void setMbKorisnik(MbKorisnik mbKorisnik) {
        this.mbKorisnik = mbKorisnik;
    }

    private void posaljiMail(Student student, Profesor profesorId, Termin t) {
        
        //ToDo change username and password for google account
        final String username = "*****";
        final String password = "*****";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(profesorId.getEmail()));
            message.setSubject("Konsultacije");
            message.setText("Poštovani " + profesorId.getIme() + " " + profesorId.getPrezime() + ","
                    + "\n\n Student " + student.getIme() + " " + student.getPrezime()
                    + " je zakazao termin konsultacija"
                    + " za datum " + t.getTerminPK().getVreme() + ".");
            Transport.send(message);
            
            System.out.println("Message sent");
        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
}
