/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Date;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Korisnik;
import model.Profesor;

/**
 *
 * @author Jelena
 */
@Stateless
public class KorisnikServiceBean implements KorisnikServiceBeanLocal {
    @PersistenceContext(unitName = "OfficehoursManagment-ejbPU")
    private EntityManager em;

    @Override
    public Korisnik findUserByLoginName(String email, String password) throws Exception{
        Korisnik k = nadjiKorisnika(email);
        proveriPassword(k, password);
        return k;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private Korisnik nadjiKorisnika(String email) throws Exception {
        try {
            return (Korisnik) em.createNamedQuery("Profesor.findByEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            try{
                return (Korisnik) em.createNamedQuery("Student.findByEmail").setParameter("email", email).getSingleResult();
            } catch (NoResultException ex){
                throw new Exception("Korisnik ne postoji u sistemu.");
            }
        }
    }

    private void proveriPassword(Korisnik k, String password) throws Exception {
        if(!k.getPassword().equals(password)) 
            throw new Exception("Pogresan password.");
    }
    
}
