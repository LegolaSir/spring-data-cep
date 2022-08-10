package com.digitalinnovation.springdatacpf.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_addresses")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @Column(length = 8)
    private String zipCode;

    @Column(nullable = false)
    private String addressName;

    @Column
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String province;

    @Column(length = 3)
    private String areaCode;

    @Column
    private String additionalInfo;
}
