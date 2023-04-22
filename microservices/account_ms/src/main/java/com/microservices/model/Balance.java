package com.activedge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,orphanRemoval = false)
    private User user ;
    @Column(name = "accountname")
    private String accountname ;

    @Column(name = "accountBalance")
    private BigDecimal accountBalance ;

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
