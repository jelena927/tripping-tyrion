/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import model.Korisnik;
import model.Student;

/**
 *
 * @author Jelena
 */
@Local
public interface KorisnikServiceBeanLocal {

    Korisnik findUserByLoginName(String email) throws Exception;
    void dodajStudenta(Student s) throws Exception;
}
