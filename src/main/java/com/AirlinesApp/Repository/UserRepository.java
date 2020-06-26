package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Person;
import com.AirlinesApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    User findOneById(Long userId);

    User findOneByPersonID(Person personID);

    @Modifying
    @Query("Update User u SET u.password=:password WHERE u.id=:id")
    void changePassword(@Param("id") Long id, @Param("password") String password);
}