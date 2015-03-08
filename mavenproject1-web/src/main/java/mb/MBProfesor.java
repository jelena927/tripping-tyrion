/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.SBProfesorLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Predmet;
import model.Profesor;

/**
 *
 * @author admin
 */
@ManagedBean(name = "mbProfesor")
@SessionScoped
public class MBProfesor implements Serializable {
    @EJB
    private SBProfesorLocal sBProfesor;
    
    /**
     * Creates a new instance of MBProfesor
     */
    public MBProfesor() {
    }
    
    public List<Profesor> vratiProfesore(String prefix, Predmet predmet) {
//        System.out.println("prefix=" + prefix);
//        System.out.println("pred=" + predmet.getPredmetId());
//        if(prefix == null)
//            return sBProfesor.vratiProfesorePoPredmetu(predmet.getPredmetId());
//        return sBProfesor.vratiProfesorePoPredmetuIPrefixu(prefix, predmet.getPredmetId());
        return null;
    }

    public List<Profesor> vratiSveProfesore() {
         return sBProfesor.vratiSveProfesore();
    }
    
}
