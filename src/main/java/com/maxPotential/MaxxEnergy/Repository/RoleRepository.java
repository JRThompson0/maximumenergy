package com.maxPotential.MaxxEnergy.Repository;


import com.maxPotential.MaxxEnergy.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleName (String roleName);

}