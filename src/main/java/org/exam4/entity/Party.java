package org.exam4.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode
@NonNull
public class Party extends BaseId {

    private boolean opened;


    private LocalDateTime createdOn;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "mountain_id")
    private Mountain mountain;

    @OneToMany(mappedBy = "party", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Climber> climbers = new LinkedHashSet<>();

    public Party() {
    }

    public Party(boolean opened, Mountain mountain, Set<Climber> climbers) {
        this.opened = opened;
        this.mountain = mountain;
        this.climbers = climbers;
        this.createdOn=LocalDateTime.now();
    }
}
