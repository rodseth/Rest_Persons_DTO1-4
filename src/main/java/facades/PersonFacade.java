/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import Interfaces.IPersonFacade;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author MariHaugen
 */
public class PersonFacade implements IPersonFacade {
    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }        
    
    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        PersonsDTO personsDTO = new PersonsDTO(persons);
        return personsDTO;

    }

    @Override
    public PersonDTO getPerson(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO deletePerson(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TODO - TEST - SKAL SLETTES
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Mari", "Haugen", "123456");
        Person p2 = new Person("Cathrine", "Christensen", "654321");

        em.getTransaction().begin();

        em.persist(p1);
        em.persist(p2);

        em.getTransaction().commit();


    }
    
}
