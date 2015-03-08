/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sequrity;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.Korisnik;
import model.Student;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
@PersistenceContext(name="ime", unitName="OfficehoursManagment-ejbPU")
public class LoginService implements UserDetailsService{
    
    public static final String ROLE_STUDENT = "student";
    public static final String ROLE_PROFESOR = "profesor";
    
    public LoginService(){
    }
 
    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        
        Korisnik user = null;
        try {
            //username je email u ovom slucaju
            user = loadUserByEmail(username );
            
        } catch (Exception ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        if( user == null )
            throw new UsernameNotFoundException("Ne postoji registrovani korisnik sa email adresom " + username );
 
        List<SimpleGrantedAuthority> authorities;
        if(user instanceof Student)
            authorities = Arrays.asList( new SimpleGrantedAuthority(ROLE_STUDENT));
        else
            authorities = Arrays.asList( new SimpleGrantedAuthority(ROLE_PROFESOR));
 
        return new User( username, user.getPassword(), authorities);
    }

    public Korisnik loadUserByEmail(String email) throws Exception {
        Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
        EntityManagerFactory emf =  (EntityManagerFactory) envCtx.lookup("ime");
        EntityManager em = emf.createEntityManager();
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
    
}
