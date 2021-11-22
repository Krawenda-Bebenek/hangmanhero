package com.ekb.hangmanhero.login.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "role", schema = "admin")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean deletable;

    @Builder.Default
    @OneToMany(mappedBy = "role")
    private Set<SystemUser> systemUsers = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = EAGER)
    @JoinTable(schema = "admin", name = "permission_role",
            joinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission", referencedColumnName = "id")})
    @OrderBy("id ASC")
    private Set<Permission> permissions = new HashSet<>();
}

