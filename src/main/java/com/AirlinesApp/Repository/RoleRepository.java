
package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.ERole;
import com.AirlinesApp.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozytorium ról
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Zwraca rolę po nazwie
     * @param name - nazwa
     * @return Role
     */
    Optional<Role> findByName(ERole name);
}