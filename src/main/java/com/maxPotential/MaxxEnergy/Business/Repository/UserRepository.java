package com.maxPotential.MaxxEnergy.Business.Repository;


import com.maxPotential.MaxxEnergy.Business.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}