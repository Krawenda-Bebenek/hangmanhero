package com.ekb.hangmanhero.login.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "system_user", schema = "admin")
public class SystemUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(length = 20)
    private String name;

    @Column(length = 30)
    private String surname;

    @Column(length = 15)
    private String phone;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

    @OneToOne(mappedBy = "systemUser")
    private SystemUserToken systemUserToken;

    @Column(length = 32)
    private String resetPasswordToken;

    private Timestamp resetPasswordExpireTime;

    @CreationTimestamp
    @Column(name = "creation_time", updatable = false)
    private Timestamp creationTime;

    @UpdateTimestamp
    @Column(name = "modification_time")
    private Timestamp modificationTime;
}