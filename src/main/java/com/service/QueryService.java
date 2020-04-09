package com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import com.entity.Benutzer;

//@DataSourceDefinition(
//name = "java:comp/DefaultDataSource",
//className = "org.postgresql.ds.PGSimpleDataSource",
//serverName = "localhost",  // set the property
//portNumber = 5432,        // set the property
//databaseName = "public",    // set the property
//user = "postgres",
//password = "bajunko13")
@Stateless
public class QueryService {
	
	
    @PersistenceContext(unitName = "myPU")
    EntityManager entityManager;

    @Inject
    private MySession mySession;
    
//    @Inject
//    private SecurityUtil securityUtil;
    
    
    
    
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

    

    public List countBenutzerByName(String name) {
        List resultList = entityManager.createNativeQuery("select count (id) from Benutzer where  exists (select id from Benutzer where name = ?)")
                .setParameter(1, name).getResultList();

        return resultList;
    }
    
//    public boolean authenticateUser(String name, String plainTextPassword) {
//
//        Benutzer benutzer = findBenuztezrByName(name);
//
//        if (benutzer != null) {
//            return securityUtil.passwordsMatch(benutzer.getPasswort(), benutzer.getSalt(), plainTextPassword);
//        }
//        return false;
//
//    }

}
