/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.KorisnikServiceBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Korisnik;
import model.Student;

/**
 *
 * @author admin
 */
@ManagedBean(name = "mbKorisnik")
@SessionScoped
public class MbKorisnik {
    @EJB
    private KorisnikServiceBeanLocal korisnikServiceBean;
    
    public MbKorisnik(){
        
    }
    
    public Korisnik nadjiPoMailu(String email){
        try {
            return korisnikServiceBean.findUserByLoginName(email);
        } catch (Exception ex) {
            Logger.getLogger(MbKorisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void dodajStudenta(Student s){
        try {
            korisnikServiceBean.dodajStudenta(s);
        } catch (Exception ex) {
            Logger.getLogger(MbKorisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
