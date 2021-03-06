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
import java.util.Date;
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
        
        return new PersonsDTO(persons);

    }

    @Override
    public PersonDTO getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p where p.id = :id", Person.class);
        query.setParameter("id", id);
        Person person = query.getSingleResult();
        
        return new PersonDTO(person);
        
    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(fName, lName, phone);
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
        
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO deletePerson(long id) {
        EntityManager em = emf.createEntityManager();
        //Long idLong = Long.parseLong(id+"");
        Person person = em.find(Person.class, id);
        //if(person == null ){
        //    throw new Exception("No person matches the id");
        //}
       try{ 
        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
       
        return new PersonDTO(person);
        
       } finally {
           em.close();
       }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, p.getId());
        
       try{ 
        em.getTransaction().begin();
        person.setFirstName(p.getfName());
        person.setLastName(p.getlName());
        person.setPhone(p.getPhone());
        person.setLastEdited(new Date());
        em.getTransaction().commit();
       
        return new PersonDTO(person);
        
       } finally {
           em.close();
       }
    }

}
