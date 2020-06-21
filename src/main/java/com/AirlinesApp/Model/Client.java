package com.AirlinesApp.Model;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "personID", referencedColumnName = "id")
    private Person personID;
}
