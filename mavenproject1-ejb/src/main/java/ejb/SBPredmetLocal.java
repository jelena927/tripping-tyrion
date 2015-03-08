/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;
import model.Predmet;

/**
 *
 * @author Jelena
 */
@Local
public interface SBPredmetLocal {

    void dodajPredmet(Predmet predmet);

    List<Predmet> vratiPredmete();

    public List<Predmet> vratiPredmetePoPrefiksu(String prefix);
    
    public Predmet vratiPredmet(long predmetId);
    
}
