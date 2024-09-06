package com.maxPotential.MaxxEnergy.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="u_role")
@Getter
public class Role {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long role_id;
    @Setter
    @Column (nullable = false)
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    public void setUsers(List<Role> list) {
    }
    public Role() {
        super();
    }
    public Role(long role_id, String name) {
        super();
        this.role_id = role_id;
        this.roleName = name;

    }
}
