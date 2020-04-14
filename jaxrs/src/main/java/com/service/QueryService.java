package com.service;

import java.util.List;
import java.util.TimeZone;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import com.entity.Benutzer;


/**
 * @author Ante
 *
 */
@Stateless
public class QueryService {
	
	
    @PersistenceContext(unitName = "myPU")
    EntityManager entityManager;

    
    public Benutzer findBenuztezrByName(String name) {

        try {
        	
            return entityManager.createNamedQuery(Benutzer.FIND_BENUTZER_BY_NAME, Benutzer.class)
                    .setParameter("name", name).getSingleResult();

        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }
    }

    public Benutzer findBenuztezrByNameAndPasswort(String name, String passwort) {

        try {
        	
            return entityManager.createNamedQuery(Benutzer.FIND_BENUTZER_BY_NAME_AND_PASSWORT, Benutzer.class)
                    .setParameter("name", name).setParameter("passwort", passwort).getSingleResult();

        } catch (NonUniqueResultException | NoResultException e) {
            return null;
        }
    }


    public List<Benutzer> findAllBenutzer() {

        return entityManager.createNamedQuery(Benutzer.FIND_ALL_BENUTZER, Benutzer.class).getResultList();
    }
    
    /**
    * getTimeZoneID - return id for zone as String for example Europe/London 
    * 
    *
    * @author  Ante Celic
    * @version 1.0
    * @since   
    */
    public String getTimeZoneId() {
    	return TimeZone.getDefault().getID();
    }

    

    public List countBenutzerByName(String name) {
        List resultList = entityManager.createNativeQuery("select count (id) from Benutzer where  exists (select id from Benutzer where name = ?)")
                .setParameter(1, name).getResultList();

        return resultList;
    }
    
}
