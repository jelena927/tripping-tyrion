/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konverteri;

import ejb.SBPredmetLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Predmet;

/**
 *
 * @author admin
 */

@FacesConverter("predmetKonverter")
public class PredmetKonverter implements Converter{
    SBPredmetLocal sBPredmet = lookupSBPredmetLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String predmetId = value;
        return sBPredmet.vratiPredmet(predmetId);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Predmet p = (Predmet) value;
        return p.getPredmetId();
    }

    private SBPredmetLocal lookupSBPredmetLocal() {
        try {
            Context c = new InitialContext();
            return (SBPredmetLocal) c.lookup("java:global/mavenproject1-ear-1.0-SNAPSHOT/mavenproject1-web-1.0-SNAPSHOT/SBPredmet");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
