package com.digi.model;

import com.digi.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    private Integer id;
    private String country;

    @Column(name = "state_region_province")
    private String region;
    private String city;
    private String street;

    @Column(name = "user_id")
    private Integer userId;
}
