package com.AirlinesApp.Model;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "personID", referencedColumnName = "id")
    @Getter
    private Person personID;

    @Column(name = "salary", nullable = false)
    @NotNull(message = "salary cannot be empty.")
    @Getter
    private Long salary;

    @Column(name = "hiringDate", nullable = false)
    @NotNull(message = "Hiring Date cannot be empty.")
    @Getter
    private Date hiringDate;

    @Column(name = "firingDate", nullable = true)
    @Getter
    private Date firingDate;

    @Column(name = "position", nullable = false)
    @NotNull(message = "Position cannot be empty.")
    @Getter
    private String position;

    public Employee(){}
    public Employee(Person person, Long salary, Date hiringDate, String position){
        this.personID = person;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.position = position;
    }
    public Person getPersonID(){
        return personID;
    }
}
