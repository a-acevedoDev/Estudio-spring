package com.spring.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class Audit {
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Evento de ciclo de vida entity: PRE PERSIST.");
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Evento de ciclo de vida entity: PRE UPDATE.");
        this.updateAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Audit{" +
                "createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
