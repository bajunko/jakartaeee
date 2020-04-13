package com.jsf.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.entity.Benutzer;
import com.service.PersistenceService;
import com.service.QueryService;


@Named
@SessionScoped
public class BenutzerManagmentBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Inject
    private ExternalContext externalContext;
    
	@Inject
	QueryService quService;
	
	@Inject
	PersistenceService persistenceService;
	
	private List<Benutzer> benutzerList = new ArrayList<Benutzer>();
	
	private Benutzer selectedBenutzer;
	private List<Benutzer> selectedBenutzers;
	
	private Benutzer benuter;
	private Long id; // For delete benuzter by id
	

	
	private boolean privilegiertByBenutzer;
	private String inputTimeZone;
	private String textVergleichen;
	
	//Check is Benutzer have Privilegiert
	private boolean findPrivilegiertByName() {
		String benutzerNameLogged = getBenuter().getName();
		Benutzer benutzer = quService.findBenuztezrByName(benutzerNameLogged);
		if(benutzer.isPrivilegt()) {
			return true;
		}else {
			return false;
		}
	}

	public Benutzer getBenuter() {
		return benuter;
	}

	public void setBenuter(Benutzer benuter) {
		this.benuter = benuter;
	}

	public List<Benutzer> getBenutzerList() {
		return this.benutzerList = quService.findAllBenutzer();
	}

	public void setBenutzerList(List<Benutzer> benutzerList) {
		this.benutzerList = benutzerList;
	}

	public List<Benutzer> getSelectedBenutzers() {
		return selectedBenutzers;
	}

	public void setSelectedBenutzers(List<Benutzer> selectedBenutzers) {
		this.selectedBenutzers = selectedBenutzers;
	}
	
	public void indexPage() {
		   try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/app/index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void auflistenPage() {
		   try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/app/auflisten.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//Simple REST client	
	
  private static final String REST_URI 
    = "http://localhost:8080/jakartaee8-starter/api/benutzer/time";

  private Client client = ClientBuilder.newClient();

  public Response getRestTime() {
      return client
        .target(REST_URI)
        .request(MediaType.TEXT_PLAIN).get();
        
  }
  
  public String getRestTimeZone() {
	 return "Aktuelle Zeit des Servres: " + getRestTime().readEntity(String.class);
  }
  
  public void vergleichenTimeZone() {
	  
	  if(getRestTimeZone().contains(getInputTimeZone())) {
                  setTextVergleichen("Sie befinden sich in derselben Zeitzone");
	  }else {
		  setTextVergleichen("Sie befinden nicht sich in derselben Zeitzone");
	  }
	  
	  
  }
	
	
	
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Benutzer Selected");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Benutzer Unselected");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void deleteSelctedBenutzer(long id) {
    	System.out.println("U akcije za brisanje smo");
//    	Long hdId = new Long(51);
    	persistenceService.deleteBenutzer(getId());
    }

	public Benutzer getSelectedBenutzer() {
		return selectedBenutzer;
	}

	public void setSelectedBenutzer(Benutzer selectedBenutzer) {
		this.selectedBenutzer = selectedBenutzer;
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPrivilegiertByBenutzer() {
		return privilegiertByBenutzer = findPrivilegiertByName();
	}

	public String getInputTimeZone() {
		return inputTimeZone;
	}

	public void setInputTimeZone(String inputTimeZone) {
		this.inputTimeZone = inputTimeZone;
	}

	public String getTextVergleichen() {
		return textVergleichen;
	}

	public void setTextVergleichen(String textVergleichen) {
		this.textVergleichen = textVergleichen;
	}

}
