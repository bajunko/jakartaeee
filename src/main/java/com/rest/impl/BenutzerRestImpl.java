package com.rest.impl;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.Benutzer;
import com.service.PersistenceService;
import com.service.QueryService;


@Path("benutzer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BenutzerRestImpl{


    @Inject
    private PersistenceService persistenceService;

    @Inject
    private QueryService queryService;
    

    @Path("create")
    @POST
    public Response createTodoUser(@NotNull @Valid Benutzer benutzer) {


        persistenceService.saveBenutzer(benutzer);

        return Response.ok(benutzer).build();

    }


    @DELETE
    @Path("delete/{id}")
    public Response deleteBenutzer(@PathParam("id") Long id) {
        

        return Response.ok(persistenceService.deleteBenutzer(id)).build();
    }
    
    @GET
    @Path("users")
    public Response listAllTodoUsers() {
        return Response.ok(queryService.findAllBenutzer()).build();
    }
    
    
    @GET
    @Path("time")
    public Response time() {
    	
        return Response.ok(queryService.findAllBenutzer()).build();
    }
    

    
}
