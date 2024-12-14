package com.ecommerce.user_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user_service.Exceptions.UsernameDuplicationException;
import com.ecommerce.user_service.dto.UserDTO;
import com.ecommerce.user_service.service.UserService;

@RestController
@RequestMapping("api/user")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAll(){
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{userName}")
    public ResponseEntity<Optional<UserDTO>> getUser(@PathVariable String userName){
        return new ResponseEntity<>(this.userService.getUser(userName), HttpStatus.OK);
    }

    @GetMapping("/checkUser/{userName}")
    public ResponseEntity<Boolean> checkUser(@PathVariable String userName){
        Boolean result=this.userService.checkUser(userName);
        return new ResponseEntity<>(this.userService.checkUser(userName), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user) {
        try {
            UserDTO  addedUser = userService.addUser(user);
            return ResponseEntity.ok(addedUser);
        } catch (UsernameDuplicationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request.");
        }
    }

    @PutMapping("/editUser")
    public ResponseEntity<UserDTO > editUser(@RequestBody UserDTO user){
        return new ResponseEntity<>(this.userService.editUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        return new ResponseEntity<>(this.userService.deleteUser(userName), HttpStatus.OK);
    }
}
