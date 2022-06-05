package org.exam4.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@MappedSuperclass
public abstract class BaseId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL
    private int id;
}
