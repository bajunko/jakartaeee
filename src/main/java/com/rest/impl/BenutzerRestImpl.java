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


    //GET
    //POST
    //PUT
    //DELETE

    @Inject
    private PersistenceService persistenceService;

    @Inject
    private QueryService queryService;


    @Path("create") //api/v1/user/create -TodoUser(Json) - POST
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
//
//
//    @GET
//    @Path("find/{email}") // /api/v1/user/find/{email}/users/{age}
////    @Consumes(MediaType.APPLICATION_JSON)
////    @Produces(MediaType.APPLICATION_JSON)
//    public TodoUser findTodoUserByEmail(@NotNull @PathParam("email") String email) {
//
//        return queryService.findTodoUserByEmail(email);
//    }
//
//
//    @GET
//    @Path("query") // /api/v1/user/query?email=bla@bla.com
////    @Consumes(MediaType.APPLICATION_JSON)
////    @Produces(MediaType.APPLICATION_JSON)
//    public TodoUser findTodoUserByEmailQueryParam(@NotNull @QueryParam("email") String email) {
//
//        return queryService.findTodoUserByEmail(email);
//    }
//
//
//    @GET
//    @Path("search")
//    public Response searchTodoUserByName(@NotNull @QueryParam("name") String name) {
//
//        return Response.ok(queryService.findTodoUsersByName(name)).build();
//    }
//
//    @GET
//    @Path("count")
//    public Response countTodoUserByEmail(@QueryParam("email") @NotNull String email) {
//
//        return Response.ok(queryService.countTodoUserByEmail(email)).build();
//    }

    @GET
    @Path("list")
    public Response listAllTodoUsers() {
        return Response.ok(queryService.findAllBenutzer()).build();
    }

    
}
