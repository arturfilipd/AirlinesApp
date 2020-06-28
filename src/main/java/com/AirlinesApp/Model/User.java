package com.AirlinesApp.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Klasa modelująca użytkownika
 */
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Setter
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Setter
    private String email;

    @NotBlank
    @Size(max = 120)
    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Setter
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "personID", referencedColumnName = "id")
    @Setter
    private Person personID;


    //2OAuth
    @Getter @Enumerated(EnumType.STRING) @Setter private AuthProvider provider;
    @Getter @Setter private String providerId;
    @Getter @Setter private String imageUrl;

    /**
     * Konstruktor domyślny
     */
    public User() {
    }

    /**
     * Konstruktor
     * @param username - nazwa użytkownika
     * @param email - mail
     * @param password - hasło
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Setter osoby
     * @param p - osoba
     */
    public void setPersonID(Person p) {personID = p;}

    /**
     * Getter id
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter id
     * @param id - id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter nazwy użytkownika
     * @return username - nazwa
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter nazwy użytkownika
     * @param username - nazwa
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter emailu
     * @return email - email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter maila
     * @param email - email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter hasła
     * @return password - hasło
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter hasła
     * @param password - hasło
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter ról
     * @return roles - role
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Setter ról
     * @param roles - role
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}