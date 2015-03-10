/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Konsultacije;

/**
 *
 * @author admin
 */
@Stateless
public class SBKonsultacije implements SBKonsultacijeLocal {

    @PersistenceContext(unitName = "OfficehoursManagment-ejbPU")
    private EntityManager em;
    
    @Override
    public void dodajKonsultacije(Konsultacije k) {
        em.persist(k);
    }
    
    @Override
    public Konsultacije vratiKonsultacije(long konsultacijeId) {
        return (Konsultacije) em.createNamedQuery("Konsultacije.findByKonsultacijeId").setParameter("konsultacijeId", konsultacijeId).getSingleResult();
    }
    
    @Override
    public void obrisiKonsultacije(Konsultacije k) {
//        Query q = em.createNamedQuery("Konsultacije.findByKonsultacijeId").setParameter("konsultacijeId", k.getKonsultacijeId());
//        Konsultacije kons = (Konsultacije) q.getSingleResult();
//        kons.setPredmetId(k.getPredmetId());
        em.remove(vratiKonsultacije(k.getKonsultacijeId()));
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
