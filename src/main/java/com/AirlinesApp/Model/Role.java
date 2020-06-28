package com.AirlinesApp.Model;

import javax.persistence.*;

/**
 * Klasa modelująca role
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    /**
     * Konstruktor domyślny
     */
    public Role() {

    }

    /**
     * Konstruktor
     * @param name - nazwa roli
     */
    public Role(ERole name) {
        this.name = name;
    }

    /**
     * Getter ID
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter ID
     * @param id - id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter nazwy
     * @return name - nazwa
     */
    public ERole getName() {
        return name;
    }

    /**
     * Setter nazwy
     * @param name - nazwa
     */
    public void setName(ERole name) {
        this.name = name;
    }
}