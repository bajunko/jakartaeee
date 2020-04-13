package com.service;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class MySession implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
