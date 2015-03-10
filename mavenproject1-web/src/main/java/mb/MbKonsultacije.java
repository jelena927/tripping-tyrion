/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.SBKonsultacijeLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import model.Konsultacije;
import model.Profesor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author admin
 */
@ManagedBean(name = "mbKonsultacije")
@SessionScoped
public class MbKonsultacije {
    @EJB
    private SBKonsultacijeLocal sBKonsultacijeLocal;
    @ManagedProperty("#{mbKorisnik}")
    private MbKorisnik mbKorisnik;

    public MbKorisnik getMbKorisnik() {
        return mbKorisnik;
    }

    public void setMbKorisnik(MbKorisnik mbKorisnik) {
        this.mbKorisnik = mbKorisnik;
    }
    /**
     * Creates a new instance of MbKonsultacije
     */
    public MbKonsultacije() {
    }
    
    
    void obrisiKonsultacije(Konsultacije k) {
        sBKonsultacijeLocal.obrisiKonsultacije(k);
    }
    
    public void dodajKonsultacije(Konsultacije k){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        k.setProfesorId((Profesor) mbKorisnik.nadjiPoMailu(auth.getName()));
        sBKonsultacijeLocal.dodajKonsultacije(k);
    }
}
