package com.service;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.entity.Benutzer;
import com.service.MySession;
import com.service.PersistenceService;
import com.service.QueryService;

@RunWith(Arquillian.class)
public class PersistenceServiceTest {

    @Inject
    QueryService queryService;

    @Inject
    PersistenceService persistenceService;

    @Inject
    MySession mySession;

    @Inject
    Benutzer benutzer;

  


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PersistenceService.class.getPackage())
                .addPackage(Benutzer.class.getPackage())
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUp() throws Exception {

        mySession.setName("Test");

        benutzer.setName("Test");
        benutzer.setPasswort(UUID.randomUUID().toString());
        benutzer.setPrivilegt(false);

       


    }

    @After
    public void tearDown() throws Exception {
    }

  
    @Test
    public void saveBenutzer() {

        persistenceService.saveBenutzer(benutzer);





        assertNotNull(benutzer.getId());


    }

   
}
