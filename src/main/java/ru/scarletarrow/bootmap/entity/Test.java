package ru.scarletarrow.bootmap.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table
public class Test {
    @Id
    @Column(name = "test_id")
    private String testId;

    @Column(name = "location_id", insertable = false, updatable = false)
    private int locationId;

    @Column(name = "operator_id")
    private int operatorId;

    @Column(name = "download_speed")
    private double downloadSpeed;

    @Column(name = "upload_speed")
    private double uploadSpeed;

    @Column(name = "latency")
    private int latency;

    @Column(name = "date")
    private String date;

    @Column(name = "user_id")
    private String userId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "location_id", referencedColumnName = "id")
//    private Location location;


}
