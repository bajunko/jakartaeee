package com.jsf.managedbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class GreetingController {

    private String message;

    @PostConstruct
    public void init(){
        this.message = "Hello from Facelets";
    }
    
    

    public String getMessage() {
        return message;
    }
}
