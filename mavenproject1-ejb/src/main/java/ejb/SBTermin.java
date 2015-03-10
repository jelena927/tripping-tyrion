/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Konsultacije;
import model.Termin;
import model.TerminPK;

/**
 *
 * @author admin
 */
@Stateless
public class SBTermin implements SBTerminLocal {
    @PersistenceContext(unitName = "OfficehoursManagment-ejbPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public void sacuvaj(Termin t) {
        try {
            TerminPK tpk = new TerminPK();
            Konsultacije k = (Konsultacije) em.createNamedQuery("Konsultacije.findByKonsultacijeId")
                    .setParameter("konsultacijeId", t.getTerminPK().getKonsultacije().getKonsultacijeId())
                    .getSingleResult();
            tpk.setKonsultacije(k);
            tpk.setVreme(t.getTerminPK().getVreme());
            t.setTerminPK(tpk);
            em.merge(t);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Termin vratiTermin(long terminId){
        return (Termin) em.createNamedQuery("Termin.findByTerminId").getSingleResult();
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void sacuvajTermin(Termin t) {
        sacuvaj(t);
    }

}
