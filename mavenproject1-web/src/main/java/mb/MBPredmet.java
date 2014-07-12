/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import ejb.SBPredmetLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

//    @EJB(mappedName = "newSessionBean")
//    NewSessionBean newSessionBean;
    
    private static final Logger LOG = Logger.getLogger(MBPredmet.class.getName());
    /**
     * Creates a new instance of MBPredmet
     */
    public MBPredmet() {
        System.out.println("********************************************");
    }
    
    public void prijaviKorisnika(){
        LOG.log(Level.INFO, "yyyyyyyyyyyyyyyyyyyyyyyyyzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println("********************************************");
        System.out.println("********************************************");
        InitialContext ic;
        LOG.log(Level.INFO, sBPredmet.vratiPredmete().toString());
//        try {
//            ic = new InitialContext();//"java:global/EnterpriseSaberi/EJBSaberi/SessionSaberi"
//        sBPredmet = (SBPredmetLocal) ic.lookup("java:global/OfficehoursManagment-ejb/SBPredmet!ejb.SBPredmetLocal");
//        sBPredmet.vratiPredmete();
//        } catch (NamingException ex) {
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//            Logger.getLogger(MBPredmet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public List<Predmet> vratiSvePredmete(String prefix) {
        if(prefix != null)
            return sBPredmet.vratiPredmetePoPrefiksu(prefix);
        return sBPredmet.vratiPredmete();
    }
    
}
