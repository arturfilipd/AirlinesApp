package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "Select * from roles", nativeQuery = true)
    List<Role> getAllRoles();
}
