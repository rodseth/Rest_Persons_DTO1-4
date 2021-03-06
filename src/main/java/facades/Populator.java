/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.Person;
import entities.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populatePerson(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        PersonFacade fe = PersonFacade.getPersonFacade(emf);
        Person p1 = new Person("Mari", "Haugen", "123456");
        Person p2 = new Person("Cathrine", "Christensen", "654321");
        
        em.getTransaction().begin();

        em.persist(p1);
        em.persist(p2);

        em.getTransaction().commit();
    }
    
    public static void main(String[] args) {
        populatePerson();
    }
}
