package com.AirlinesApp.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Integer id;

    @ManyToOne
    @JoinColumn(name = "personID", referencedColumnName = "id")
    @Getter private Person personID;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @Getter @Setter private User userId;

}
