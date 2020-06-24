package com.AirlinesApp.Model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "People")
public class Person {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "name cannot be empty.")
    @Getter
    private String name;

    @Column(name = "surname", nullable = false)
    @NotNull(message = "surname cannot be empty.")
    @Getter
    private String surname;

    @Column(name = "personalID", nullable = false)
    @NotNull(message = "Personal ID cannot be empty.")
    @Getter
    private String personalID;

    @Column(name = "phoneNumber", nullable = false)
    @NotNull(message = "phoneNumber cannot be empty.")
    @Getter
    private String phoneNumber;

    public Person(){}

    public Person(String name, String surname, String personalID, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this.personalID = personalID;
        this.phoneNumber = phoneNumber;
    }
    /*public int getId(){
        return id;
    }*/
}
