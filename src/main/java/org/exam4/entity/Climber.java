package org.exam4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@NonNull
public class Climber extends BaseId {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String homeAddress;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "party_id")
    private Party party;

    @ManyToMany(mappedBy = "climbers",cascade = CascadeType.PERSIST)
    private Set<Party> parties;

    public void setParties(Set<Party> parties) {
        this.parties = parties;
    }

    public void setParty(Party party) {
        this.party = party;
    }


    public Climber() {
        parties=new HashSet<>();
    }
    public Climber(String name, String homeAddress) {
        parties=new HashSet<>();
        setName(name);
        setHomeAddress(homeAddress);
    }
    public void setName(String name) {
        if (name == null || name.length() < 3)
            throw new IllegalArgumentException("Значение name должно быть не менее 3 символов");
        this.name = name;
    }
    public void setHomeAddress(String homeAddress) {
        if (name == null || homeAddress.length() < 5)
            throw new IllegalArgumentException("Значение homeAddress должно быть не менее 5 символов");
        this.homeAddress = homeAddress;
    }
    public void addParty(Party party) {
        if (party == null) throw new IllegalArgumentException("party null");
        parties.add(party);
        party.getClimbers().add(this);
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "name = " + getName() + ", " +
                "homeAddress = " + getHomeAddress() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Climber climber = (Climber) o;
        return Objects.equals(getId(), climber.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
