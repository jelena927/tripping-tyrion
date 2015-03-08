/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.SBPredmetLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Predmet;

/**
 *
 * @author Jelena
 */
@ManagedBean(name = "mbPredmet")
@SessionScoped
public class MBPredmet implements Serializable {
    @EJB
    SBPredmetLocal sBPredmet;

    /**
     * Creates a new instance of MBPredmet
     */
    public MBPredmet() {
    }
    
    public List<Predmet> vratiSvePredmete(String prefix) {
        if(prefix != null)
            return sBPredmet.vratiPredmetePoPrefiksu(prefix);
        return sBPredmet.vratiPredmete();
    }
    
}
