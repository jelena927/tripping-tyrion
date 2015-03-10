/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;
import model.Konsultacije;

/**
 *
 * @author admin
 */
@Local
public interface SBKonsultacijeLocal {

    public void dodajKonsultacije(Konsultacije k);
    public Konsultacije vratiKonsultacije(long konsultacijeId);
    public void obrisiKonsultacije(Konsultacije k);
    
}
