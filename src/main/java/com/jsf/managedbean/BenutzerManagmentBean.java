package com.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

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
	QueryService quService;
	@Inject
	PersistenceService persistenceService;
	
	private List<Benutzer> benutzerList = new ArrayList<Benutzer>();
	
	private Benutzer selectedBenutzer;
	private List<Benutzer> selectedBenutzers;
	
	private Benutzer benuter;

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
	
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Benutzer Selected");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected");
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
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
