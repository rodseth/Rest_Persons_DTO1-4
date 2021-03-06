/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author allec
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static PersonFacade pf = PersonFacade.getPersonFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
           
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
        throw new UnsupportedOperationException();
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsJson() {
        return gson.toJson(pf.getAllPersons());
    }
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
