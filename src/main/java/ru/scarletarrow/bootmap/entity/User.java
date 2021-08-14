package ru.scarletarrow.bootmap.entity;

import lombok.Data;
import lombok.ToString;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "user")
@ToString
public class User {

    @Id

//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;
    @Column(name = "mail")
    private String mail;
    @Column(name = "birthdate")
    private String birthdate;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "password")
    private String password;

    @Column(name = "is_verified")
    private int isVerified;

//    @OneToMany
//    private List<Location> locations;

//    @Column(name = "test_id")
//    private String testId;

    @OneToMany
    private List<Test> tests;
}
