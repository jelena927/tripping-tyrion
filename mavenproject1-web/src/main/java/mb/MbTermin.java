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
    
    public void dodaj(DefaultScheduleEvent e, Konsultacije k){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Termin t = new Termin();
        t.setTema(e.getTitle());
        t.setStudent((Student) mbKorisnik.nadjiPoMailu(auth.getName()));
        TerminPK tpk = new TerminPK();
        tpk.setVreme(e.getStartDate());
        tpk.setKonsultacije(k);
        t.setTerminPK(tpk);
        
        sBTermin.sacuvajTermin(t);
        posaljiMail(t.getStudent(), t.getTerminPK().getKonsultacije().getProfesorId(), t);
    }

    public MbKorisnik getMbKorisnik() {
        return mbKorisnik;
    }

    public void setMbKorisnik(MbKorisnik mbKorisnik) {
        this.mbKorisnik = mbKorisnik;
    }

    private void posaljiMail(Student student, Profesor profesorId, Termin t) {
        // Recipient's email ID needs to be mentioned.
      String to = "jelena.m.djordjevic@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "jelena927@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
        Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.smtp.starttls.enable", "true");

      // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
          MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Zakazan termin konsultacija");

         // Now set the actual message
         message.setText("Student " + student.getIme() + " " + student.getPrezime()
         + " je zakazao termin konsultacija za datum " + t.getTerminPK().getVreme()
         + " Tema je " + t.getTema());

         // Send message
          Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
    }
}
