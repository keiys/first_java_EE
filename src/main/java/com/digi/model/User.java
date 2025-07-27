package com.digi.model;

import com.digi.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
/**
 * this class mapped to db table
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private Integer id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    private Integer year;
    private String email;
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "reset_token")
    private String resetToken;

}
