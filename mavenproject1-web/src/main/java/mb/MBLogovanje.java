/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.KorisnikServiceBeanLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Korisnik;

/**
 *
 * @author Jelena
 */
@ManagedBean
@SessionScoped
public class MBLogovanje implements Serializable{

    @EJB
    private KorisnikServiceBeanLocal korisnikServiceBean;
    private Korisnik ulogovaniKorisnik;
    private String email;
    private String password;
    
    /**
     * Creates a new instance of MBLogovanje
     */
    public MBLogovanje() {
    }

    public Korisnik getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }

    public void setUlogovaniKorisnik(Korisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }
    
    public String ulogujKorisnika(){
        try {
            ulogovaniKorisnik = korisnikServiceBean.ulogujKorisnika(email, password);
            System.out.println("asdddddddddddddddddddddd");
//            FacesContext.getCurrentInstance().getExternalContext().dispatch("/index.jsp");
            return "welcomePrimefaces.xhtml";
        } catch (Exception ex) {
            Logger.getLogger(MBLogovanje.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
