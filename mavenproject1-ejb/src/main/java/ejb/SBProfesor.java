/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Profesor;

/**
 *
 * @author admin
 */
@Stateless
public class SBProfesor implements SBProfesorLocal {
    @PersistenceContext(unitName = "OfficehoursManagment-ejbPU")
    private EntityManager em;

//    @Override
//    public List vratiProfesorePoPredmetu(String predmetId) {
//        return em.createNamedQuery("Profesor.findByPredmet").setParameter("predmetId", predmetId).getResultList();
//    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

//    @Override
//    public List vratiProfesorePoPredmetuIPrefixu(String prefix, String predmetId) {
//        return em.createNamedQuery("Profesor.findByPrefixAndPredmet").setParameter("prefix", prefix)
//                .setParameter("predmetId", predmetId).getResultList();
//    }

    @Override
    public Profesor vratiProfesora(String profesorId) {
        return (Profesor) em.createNamedQuery("Profesor.findByProfesorId").setParameter("profesorId", profesorId).getSingleResult();
    }

    @Override
    public List<Profesor> vratiSveProfesore() {
        return em.createNamedQuery("Profesor.findAll").getResultList();
    }
    
    
}
