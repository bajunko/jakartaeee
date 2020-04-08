package com.jsf.managedbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class SampleBean {

	
	private String message = "JSF 2.3 pomocu JAVA EE JAKRTA";

	public String getMessage() {
		return this.message;
	}
}