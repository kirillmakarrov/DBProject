package org.exam4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.exam4.entity.Climber;
import org.exam4.entity.Mountain;
import org.exam4.entity.Party;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Climber climber1 = new Climber("Climber1", "Russia");
        Climber climber2 = new Climber("Climber2", "Australia");
        Climber climber3 = new Climber("Climber3", "China");
        Climber climber4 = new Climber("Climber4", "Japan");
        Mountain elbrus = new Mountain("Эльбрус", "Russia", 7000);
        Mountain ural = new Mountain("Урал", "Russia", 5000);
        Set<Climber> climbers = new HashSet<>();
        Set<Climber> climbers1 = new HashSet<>();
        climbers.add(climber1);
        climbers.add(climber2);
        climbers1.add(climber3);
        climbers1.add(climber4);
        Party party = new Party(true, elbrus, climbers);
        climber4.setParty(party);
        Party party1=new Party(false,elbrus,climbers1);

        /*try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("orm");
             EntityManager manager = factory.createEntityManager()){
            manager.getTransaction().begin();
            manager.persist(party);
            manager.persist(climber4);
            manager.getTransaction().commit();
        }*/
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("orm");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(party);
        manager.persist(party1);
        manager.getTransaction().commit();

    }
}
