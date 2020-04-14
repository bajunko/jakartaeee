package com.jsf.managedbean;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.entity.Benutzer;
import com.service.PersistenceService;

@Named
@RequestScoped
public class CreateBean {

   
    
    @NotEmpty(message = "A benutzername must be set")
    private String benutzername;


    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 100, message = "Password must be a min of 8 and max of 100 characters")
    private String passwort; 
    
    @NotNull(message = "Privilegt must be choose")
    private boolean privilegt;


    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;
    
    @Inject
    private PersistenceService persistenceService;

   
    
    public void createPage() {
    	
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    	try {
			context.redirect(context.getRequestContextPath() + "/create.xhtml");
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void createPageFromUser() {
    	
    	ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    	try {
			context.redirect(context.getRequestContextPath() + "/app/createFromUser.xhtml");
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void create() throws IOException {

    	         
    	       Benutzer benutzer = new Benutzer();
    	       benutzer.setName(getBenutzername());	 
    	       benutzer.setPasswort(getPasswort());
    	       benutzer.setPrivilegt(isPrivilegt());
			persistenceService.saveBenutzer(benutzer);
    	      
			// Ako je uspjeno kreiran ondnda odmah auteciramo
    	
			switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            case SUCCESS:
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
                externalContext.redirect(externalContext.getRequestContextPath() + "/app/index.xhtml");
                break;
            case NOT_DONE:
        }
              
        
    }
    
    public void createFromUser() throws IOException {

        
	       Benutzer benutzer = new Benutzer();
	       benutzer.setName(getBenutzername());	 
	       benutzer.setPasswort(getPasswort());
	       benutzer.setPrivilegt(isPrivilegt());
		persistenceService.saveBenutzer(benutzer);
	      
		// Ako je uspjeno kreiran ondnda prikazati poruku
         facesContext.addMessage(null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO, "Benutzer erstellt", null));
         externalContext.redirect(externalContext.getRequestContextPath() + "/app/index.xhtml");
    
       
 
}    
    
    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(getBenutzername(), getPasswort())
                		)
        );
    }

   

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public boolean isPrivilegt() {
		return privilegt;
	}

	public void setPrivilegt(boolean privilegt) {
		this.privilegt = privilegt;
	}
}
