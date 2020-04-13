package com.service;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.entity.Benutzer;



@Stateless
public class PersistenceService {


    @Inject
    private MySession mySession;

    @Inject
    private QueryService queryService;


    @PersistenceContext
    EntityManager entityManager;        //new SomeConcreteImplementation;
    
    
    public Benutzer saveBenutzer(Benutzer benutzer) {
        //TODO Implement
        //someInterface.saveToDB(todoUser)



            entityManager.persist(benutzer); //#save}

        return benutzer;
    }
    
    public int deleteBenutzer(long benutzerId) {
      
    	  try {
              return  entityManager.createNamedQuery(Benutzer.DELETE_BENUTZER_BY_ID, Benutzer.class)
                      .setParameter("id", benutzerId).executeUpdate();

          } catch (NonUniqueResultException | NoResultException e) {
              return 0;
          }
    }
    
    
    
        

  
}
