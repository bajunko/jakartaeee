package com.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.entity.Benutzer;
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
	
	private List<Benutzer> benutzerList = new ArrayList<Benutzer>();
	
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

}
