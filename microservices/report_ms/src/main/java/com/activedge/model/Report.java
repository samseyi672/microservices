package com.activedge.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,orphanRemoval = false)
    private User user ;  //this will enable us to track report for every user in the db.It is an owning side

    @Column(name = "info")
    private String reportInfo ;

    @Column(name = "dateofcreation")
    private Date dateofcreation;

    @PrePersist
    protected void onCreate() {
        this.dateofcreation = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateofcreation = new Date();
    }
}









































