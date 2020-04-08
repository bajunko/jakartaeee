package com.jsf.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.entity.Benutzer;
import com.service.QueryService;

@Named
@ViewScoped
public class TestBean  implements Serializable{
	
	@Inject
	private QueryService persistenceService;
	 /**
	 * 
	 */
	private static final long serialVersionUID = 8434721710882382193L;

	@PostConstruct
	public void init() {
		 Benutzer findBenuztezrByName = persistenceService.findBenuztezrByName("luka");
		 this.name = findBenuztezrByName.getName();
	}


	
	 
	 
	 private String name;


	public String getName() {
		return name;
	}


	 
}
