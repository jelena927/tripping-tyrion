/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.SBTerminLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.Konsultacije;
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
    }

    public MbKorisnik getMbKorisnik() {
        return mbKorisnik;
    }

    public void setMbKorisnik(MbKorisnik mbKorisnik) {
        this.mbKorisnik = mbKorisnik;
    }

    void obrisiKonsultacije(Konsultacije k) {
        sBTermin.obrisiKonsultacije(k);
    }
    
}
