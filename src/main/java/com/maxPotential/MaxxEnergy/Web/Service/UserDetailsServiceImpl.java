package com.maxPotential.MaxxEnergy.Web.Service;

import com.maxPotential.MaxxEnergy.Model.User;
import com.maxPotential.MaxxEnergy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user from the database
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User userExists = userOptional.get();
        // Convert your User entity to UserDetails
        return new org.springframework.security.core.userdetails.User(
                userExists.getUsername(),
                userExists.getPassword(),
                userExists.isEnabled(),
                userExists.isAccountNonExpired(), // account non-expired
                userExists.isCredentialsNonExpired(), // credentials non-expired
                userExists.isAccountNonLocked(), // account non-locked
                getAuthorities(userExists) // roles
        );
    }
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}


