/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
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
    
    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) {
        return gson.toJson(pf.getPerson(id));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson (String person) {
        PersonDTO personDTO = gson.fromJson(person, PersonDTO.class);
        
        PersonDTO personAdded = pf.addPerson(personDTO.getfName(), personDTO.getlName(), personDTO.getPhone());
        
        return gson.toJson(personAdded);
        
    }
    
}
