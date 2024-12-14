package com.ecommerce.user_service.dto;

import com.ecommerce.user_service.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userName;
    private String password;
    private String email;
    private String name;
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    // Optional: Static method to convert User entity to UserDTO (for convenience)
    public static UserDTO fromEntity(User user) {
        return new UserDTO(
            user.getUserName(),
            user.getPassword(),
            user.getEmail(),
            user.getName(),
            user.getAge(),
            user.getDob()
        );
    }

    // Optional: Convert UserDTO to User entity
    public User toEntity() {
        User user = new User();
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setName(this.name);
        user.setAge(this.age);
        user.setDob(this.dob);
        return user;
    }
}

