package com.maxPotential.MaxxEnergy.Web.Controller;

import com.maxPotential.MaxxEnergy.Business.Model.User;
import com.maxPotential.MaxxEnergy.Business.Repository.RoleRepository;
import com.maxPotential.MaxxEnergy.Business.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController
{
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public UserRepository uRepository;

    @Autowired
    public RoleRepository rRepository;

    @PostMapping("/add")
    public String saveUsers(@ModelAttribute("user") User user) throws Exception {
        String tempEmailId = user.getEmail();
        if (tempEmailId != null && !tempEmailId.isEmpty()) {
            User userObject = uRepository.findByEmail(tempEmailId);
            if (userObject != null) {
                throw new Exception("User with " + tempEmailId + " is already exist");
            }

        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(rRepository.findByRoleName("USERS"));
        uRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/add/employee")
    public String saveEMPLOYEEUsers(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(rRepository.findByRoleName("EMPLOYEE"));
        uRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/add/admin")
    public String saveADMINUsers(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(rRepository.findByRoleName("ADMIN"));
        uRepository.save(user);
        return "redirect:/login";
    }
}
