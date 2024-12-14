package com.ecommerce.user_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {

    @Id
    private String userName;
    private String password;
    private String email;
    private String name;
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
}
