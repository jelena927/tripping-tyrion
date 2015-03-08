/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Profesor;

/**
 *
 * @author admin
 */
@Local
public interface SBProfesorLocal {

//    List vratiProfesorePoPredmetu(String predmetId);

//    List vratiProfesorePoPredmetuIPrefixu(String prefix, String predmetId);
    
    Profesor vratiProfesora(long profesorId);

    public List<Profesor> vratiSveProfesore();
    
}
