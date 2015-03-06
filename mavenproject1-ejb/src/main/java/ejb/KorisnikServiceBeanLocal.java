/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Date;
import javax.ejb.Local;
import model.Korisnik;

/**
 *
 * @author Jelena
 */
@Local
public interface KorisnikServiceBeanLocal {

    Korisnik findUserByLoginName(String email, String password) throws Exception;
    
}
