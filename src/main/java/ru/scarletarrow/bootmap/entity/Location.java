package ru.scarletarrow.bootmap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "location_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "typeid")
    private int typeid;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "test_id")
    private String test_id;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
//    private Test test;
}
