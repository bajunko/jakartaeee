package com.jsf.security;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import com.entity.Benutzer;
import com.jsf.managedbean.BenutzerManagmentBean;
import com.service.QueryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ApplicationScoped
public class CustomInMemoryIdentityStore implements IdentityStore {
	
	@Inject
	private QueryService queryService;
	
	@Inject
	private BenutzerManagmentBean benuterManagment;
	
	
	public Benutzer provjeri(String name, String passwort) {
		return queryService.findBenuztezrByNameAndPasswort(name, passwort);
	}

    @Override
    public CredentialValidationResult validate(Credential credential) {
    	
    	List<Benutzer> listaUsera = new ArrayList<Benutzer>();
    	Benutzer b = new Benutzer();
    	b.setName("Ante");
    	b.setPasswort("123456789");
    	Benutzer b1 = new Benutzer();
    	b1.setName("Mate");
    	b1.setPasswort("Mate1234");
    	listaUsera.add(b1);

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        
        Benutzer provjeri = provjeri(login.getCaller(), login.getPasswordAsString());
        boolean autentciran = false;
        
     
		
    	  if (login.getCaller().equals(provjeri.getName()) && login.getPasswordAsString().equals(provjeri.getPasswort())) {
    		  autentciran = true;
	      }
    	  
      
      if(autentciran) {
    	  benuterManagment.setBenuter(provjeri);
    	  return new CredentialValidationResult(provjeri.getName(), new HashSet<>(Arrays.asList("USER")));
      }else {
    	  
      }     return CredentialValidationResult.NOT_VALIDATED_RESULT;

        
    }
}
