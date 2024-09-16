package com.maxPotential.MaxxEnergy.Web.Service;

import com.maxPotential.MaxxEnergy.Exception.DuplicateUserException;
import com.maxPotential.MaxxEnergy.Model.Role;
import com.maxPotential.MaxxEnergy.Model.User;
import com.maxPotential.MaxxEnergy.Repository.RoleRepository;
import com.maxPotential.MaxxEnergy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public void createNewUser(User user) throws DuplicateUserException
    {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateUserException("Email address already in use.");
        }
        existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new DuplicateUserException("Username is already in use!");
        }
        userRepository.save(user);
    }

    public String AddRoleToUser(String userName, String roleName)
    {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if(userOptional.isEmpty())
        {
            throw new UsernameNotFoundException("Selected user not found");
        }
        User userExists = userOptional.get();
        List<Role> roles = userExists.getRoles();
        Role addRole = roleRepository.findByRoleName(roleName);
        if(roles.contains(addRole))
        {
            return "User already has this role!";
        }
        else {
            roles.add(addRole);
            userExists.setRoles(roles);
            return (roleName + " role added to " + userExists.getUsername() + "'s account");
        }
    }
}
