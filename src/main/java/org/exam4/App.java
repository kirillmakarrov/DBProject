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
        Party party = new Party(true);
        Party party1=new Party(false);
        party.addClimber(climber1);
        party1.addClimber(climber1);
        party.getClimbers().add(climber2);
        climber2.setParty(party);

        party.addClimber(climber3);
        party1.addClimber(climber4);
        elbrus.addParty(party);
        elbrus.addParty(party1);

        /*try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("orm");
             EntityManager manager = factory.createEntityManager()){
            manager.getTransaction().begin();
            manager.persist(party);
            manager.getTransaction().commit();
        }*/
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("orm");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(party);
        manager.persist(party1);
        manager.persist(climber3);
        manager.getTransaction().commit();

    }
}
