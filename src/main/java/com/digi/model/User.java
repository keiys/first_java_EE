package com.digi.model;

import com.digi.enums.Status;
import lombok.*;

/**
 * this class mapped to db table
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String surname;
    private Integer year;
    private String email;
    private String password;
    private String verificationCode;
    private Status status;
    private String resetToken;

}
