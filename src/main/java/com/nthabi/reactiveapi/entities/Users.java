package com.nthabi.reactiveapi.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "users")
public class Users {

    @Id
    private UUID id;

    @Column("first_name")
    private String firstname;

    @Column("last_name")
    private String lastname;

    private String email;

    private String password;
}
