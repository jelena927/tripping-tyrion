/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author admin
 */
@ManagedBean
@RequestScoped
public class LoginView {

    public LoginView() {
        
    }
     
    public void loginError() {
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greška!", "Prijava na sistem nije uspela. Pokušajte ponovo."));
    }
    
}
