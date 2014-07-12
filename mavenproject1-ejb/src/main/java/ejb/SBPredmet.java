/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Predmet;

/**
 *
 * @author Jelena
 */
@Stateless
public class SBPredmet implements SBPredmetLocal {
    @PersistenceContext(unitName = "OfficehoursManagment-ejbPU")
    private EntityManager em;

    @Override
    public void dodajPredmet(Predmet predmet) {
        em.persist(this);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Predmet> vratiPredmete() {
        return em.createNamedQuery("Predmet.findAll").getResultList();
    }
    
    @Override
    public List<Predmet> vratiPredmetePoPrefiksu(String prefix) {
        return em.createNamedQuery("Predmet.findByPrefix").setParameter("naziv", prefix + "%").getResultList();
    }

    @Override
    public Predmet vratiPredmet(String predmetId) {
        return (Predmet) em.createNamedQuery("Predmet.findByPredmetId").setParameter("predmetId", predmetId).getSingleResult();
    }
    
}
