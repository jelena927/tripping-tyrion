/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konverteri;

import ejb.SBTerminLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Termin;

/**
 *
 * @author admin
 */
@FacesConverter("terminKonverter")
public class TerminKonverter implements Converter{
    SBTerminLocal sBTermin = lookupSBTerminLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return sBTermin.vratiTermin(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Termin)value).getTerminPK().getTerminId();
    }

    private SBTerminLocal lookupSBTerminLocal() {
        try {
            Context c = new InitialContext();
            return (SBTerminLocal) c.lookup("java:app/mavenproject1-ejb-1.0-SNAPSHOT/SBTermin");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
