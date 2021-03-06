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

    @Override
    public PersonsDTO getAllPersons() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p for Person p", Person.class);
        List<Person> persons = query.getResultList();

        return new PersonsDTO(persons);

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

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Mari", "Haugen", "123456");
        Person p2 = new Person("Cathrine", "Christensen", "654321");

        em.getTransaction().begin();

        em.persist(p1);
        em.persist(p2);

        em.getTransaction().commit();

        PersonFacade pf = new PersonFacade();

        System.out.println(pf.getAllPersons());

    }

}
