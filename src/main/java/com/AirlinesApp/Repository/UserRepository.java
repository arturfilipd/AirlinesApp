package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

/**
 * Repozytorium użytkownika
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Zwraca użytkownika po nazwie
     * @param username - nazwa
     * @return User
     */
    Optional<User> findByUsername(String username);

    /**
     * Sprawdza czy user istnieje po nazwie
     * @param username - nazwa
     * @return Boolean
     */
    Boolean existsByUsername(String username);

    /**
     * Sprawdza czy user istnieje po mailu
     * @param email - mail
     * @return Boolean
     */
    Boolean existsByEmail(String email);

    /**
     * Zwraca użytkownika po mailu
     * @param email - mail
     * @return User
     */
    Optional<User> findByEmail(String email);

    /**
     * Zwraca wszsytkich użytkowników
     * @return Lista użytkowników
     */
    @Query(value = "Select * from Users", nativeQuery = true)
    List<User> getAllUsers();

    /**
     * Zwraca użytkownika po ID
     * @param userId - ID
     * @return User
     */
    User findOneById(Long userId);

    /**
     * Zwraca użytkownika po osobie
     * @param personID - osoba
     * @return User
     */
    User findOneByPersonID(Person personID);

    /**
     * Ustawia hasło
     * @param id - id
     * @param password - hasło
     */
    @Modifying
    @Query("Update User u SET u.password=:password WHERE u.id=:id")
    void changePassword(@Param("id") Long id, @Param("password") String password);
}