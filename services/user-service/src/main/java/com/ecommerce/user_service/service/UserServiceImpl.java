package com.ecommerce.user_service.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user_service.Exceptions.UserNotFoundException;
import com.ecommerce.user_service.Exceptions.UsernameDuplicationException;
import com.ecommerce.user_service.dto.LoginInfo;
import com.ecommerce.user_service.dto.UserDTO;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAll() {
        List<User> users = this.userRepository.findAll();
        // Convert each User entity to UserDTO
        return users.stream()
                    .map(UserDTO::fromEntity)  // Convert User to UserDTO
                    .toList();
    }

    @Override
    public Optional<UserDTO> getUser(String userName) {
        Optional<User> user = this.userRepository.findById(userName);
        // Convert User entity to UserDTO if present
        return user.map(UserDTO::fromEntity);
    }

    @Override
    public Boolean checkUser(String userName) {
        return this.userRepository.existsById(userName);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        if (checkUser(userDTO.getUserName())) {
            throw new UsernameDuplicationException("Username is already taken: " + userDTO.getUserName());
        } else {
            User user = userDTO.toEntity();  // Convert UserDTO to User entity
            user.setPassword(bcryptPassword(user.getPassword()));  // Encrypt password
            User savedUser = this.userRepository.save(user);  // Save User entity
            return UserDTO.fromEntity(savedUser);  // Convert saved User entity back to UserDTO
        }
    }

    @Override
    public UserDTO editUser(UserDTO userDTO) {
        if (checkUser(userDTO.getUserName())) {
            Optional<User> temp = getUser(userDTO.getUserName()).map(UserDTO::toEntity);  // Convert DTO to entity
            User existingUser = temp.get();
            existingUser.setUserName(userDTO.getUserName());
            existingUser.setName(userDTO.getName());
            existingUser.setAge(userDTO.getAge());
            User updatedUser = this.userRepository.save(existingUser);
            return UserDTO.fromEntity(updatedUser);  // Convert updated User entity to DTO
        } else {
            throw new UserNotFoundException("Username " + userDTO.getUserName() + " is Not Found ");
        }
    }

    @Override
    public String deleteUser(String userName) {
        if(checkUser(userName)){
            this.userRepository.deleteById(userName);
            return "User "+ userName +" Deleted";
        }
        else {
            throw new UserNotFoundException("Username "+userName+" is Not Found ");
        }
    }

    @Override
    public boolean userAuth(LoginInfo loginInfo) {
        Optional<User> user= this.userRepository.findById(loginInfo.getUserName());
        if (checkUser(loginInfo.getUserName())){
           return bcryptMatch(loginInfo.getPassword(),user.get().getPassword());
        }
        return false;
    }
    public String bcryptPassword(String password){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        return encodedPassword;
    }
    public boolean bcryptMatch(String usrEntered, String dbPass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return  encoder.matches(usrEntered,dbPass);
    }
    public void updatePassword(User user, String newPassword) {
        // Update the user's password
        String encodedPassword=this.bcryptPassword(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

}
