package org.exam4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;


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

    public void setParty(Party party) {
        this.party = party;
    }


    public Climber() {
    }
    public Climber(String name, String homeAddress) {
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
