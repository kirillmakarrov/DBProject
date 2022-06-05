package org.exam4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NonNull
public class Party extends BaseId {
    private boolean opened;
    private LocalDateTime createdOn;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "mountain_id")
    private Mountain mountain;
    @OneToMany(mappedBy = "party", cascade = CascadeType.PERSIST, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Climber> climbers;

    public Party() {
        this.climbers = new HashSet<>();
        this.createdOn=LocalDateTime.now();
    }
    public Party(boolean opened) {
        this.opened = opened;
        this.climbers = new HashSet<>();
        this.createdOn=LocalDateTime.now();
    }
    public Party(boolean opened, Mountain mountain) {
        this.opened = opened;
        this.mountain = mountain;
        this.climbers = new HashSet<>();
        this.createdOn=LocalDateTime.now();
    }
    public Party(boolean opened, Mountain mountain, Set<Climber> climbers) {
        this.opened = opened;
        this.mountain = mountain;
        this.climbers = climbers;
        this.createdOn=LocalDateTime.now();
    }
    public void addClimber(Climber climber){
        if (climber==null) throw new IllegalArgumentException("climber null");
        climbers.add(climber);
        climber.setParty(this);
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "opened = " + isOpened() + ", " +
                "createdOn = " + getCreatedOn() + ", " +
                "mountain = " + getMountain() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return Objects.equals(getId(), party.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
