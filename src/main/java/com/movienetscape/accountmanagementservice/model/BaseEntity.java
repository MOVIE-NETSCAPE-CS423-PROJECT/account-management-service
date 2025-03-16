package com.movienetscape.accountmanagementservice.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    private String id;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = generateId();
        }
    }

    protected abstract String generateId();
}
