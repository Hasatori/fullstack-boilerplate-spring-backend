package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,unique = true)
    @Size(min = 4, message = "name.lengthRestriction")
    private String name;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Email
    @Column
    private String requestedNewEmail;

    @OneToOne(targetEntity = FileDb.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profile_image")
    private FileDb profileImage;
    @JsonIgnore
    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    @JsonIgnore
    private String providerId;
    @JsonIgnore
    private String twoFactorSecret;

    @Column(nullable = false)
    private Boolean twoFactorEnabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<TwoFactorRecoveryCode> twoFactorRecoveryCodes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<JwtToken> jwtTokens;
}