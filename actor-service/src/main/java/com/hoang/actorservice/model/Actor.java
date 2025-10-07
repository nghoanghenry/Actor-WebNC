package com.hoang.actorservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actor_id;

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }

    public @NotNull String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@NotNull String first_name) {
        this.first_name = first_name;
    }

    public @NotNull String getLast_name() {
        return last_name;
    }

    public void setLast_name(@NotNull String last_name) {
        this.last_name = last_name;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }

    @NotNull
    String first_name;

    @NotNull
    String last_name;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    LocalDateTime last_update;
}
