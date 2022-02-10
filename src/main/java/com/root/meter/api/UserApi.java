package com.root.meter.api;

import com.root.meter.DTO.UserDTO;
import com.root.meter.model.User;
import com.root.meter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> save(@Valid @RequestBody UserDTO userDTO){
        User savedUser = userService.save(userDTO);
        if(savedUser == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
        }
    }
}
