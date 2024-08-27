package com.maxPotential.MaxxEnergy.Business.Repository;


import com.maxPotential.MaxxEnergy.Business.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleName (String roleName);

}