package com.ecommerce.user_service.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.user_service.dto.LoginInfo;
import com.ecommerce.user_service.dto.UserDTO;

public interface UserService {

    public List<UserDTO> getAll();
    public Optional<UserDTO> getUser(String userName);
    public Boolean checkUser(String userName);
    public UserDTO  addUser(UserDTO userDTO);
    public UserDTO  editUser(UserDTO userDTO);
    public String deleteUser(String userName);

    public boolean userAuth(LoginInfo loginInfo);

}
