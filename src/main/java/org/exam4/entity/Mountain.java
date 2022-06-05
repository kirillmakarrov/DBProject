package org.exam4.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@ToString
@Entity
@Getter
@EqualsAndHashCode
public class Mountain extends BaseId {
    @Column(nullable = false, unique = true)
    private String mountainName;
    @Column(nullable = false)
    private String mountainCountry;
    private int height;

    @OneToMany(mappedBy = "mountain",
            cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Party> parties;
    public void addParty(Party party){
        if (party==null) throw new IllegalArgumentException("party null");
        parties.add(party);
        party.setMountain(this);
    }

    public Mountain() {
    }

    public Mountain(String mountainName, String mountainCountry, int height) {
        this.parties = new HashSet<>();
        setMountainName(mountainName);
        setMountainCountry(mountainCountry);
        setHeight(height);
    }

    public void setMountainName(String mountainName) {
        if (mountainName == null || mountainName.length() < 4)
            throw new IllegalArgumentException("Значение mountainName должно быть не менее 4 символов");
        this.mountainName = mountainName;
    }

    public void setMountainCountry(String mountainCountry) {
        if (mountainCountry == null || mountainCountry.length() < 4)
            throw new IllegalArgumentException("Значение mountainCountry должно быть не менее 4 символов");
        this.mountainCountry = mountainCountry;
    }

    public void setHeight(int height) {
        if (height < 100) throw new IllegalArgumentException("Значение height должно быть не менее 100 метров");
        this.height = height;
    }
}
