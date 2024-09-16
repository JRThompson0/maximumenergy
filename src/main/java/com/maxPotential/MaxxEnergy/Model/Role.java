package com.maxPotential.MaxxEnergy.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="roles")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(String roleNamae)
    {
        roleName = roleNamae;
    }
    public Role()
    {
        roleName="placeholderRoleName";
    }
}
