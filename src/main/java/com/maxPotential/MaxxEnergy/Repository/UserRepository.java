package com.maxPotential.MaxxEnergy.Repository;


import com.maxPotential.MaxxEnergy.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}