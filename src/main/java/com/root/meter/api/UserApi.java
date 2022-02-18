package com.root.meter.api;

import com.root.meter.DTO.UserDTO;
import com.root.meter.model.Users;
import com.root.meter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Users> save(@Valid @RequestBody UserDTO userDTO){
        Users savedUser = userService.save(userDTO);
        if(savedUser == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<Users>(savedUser,HttpStatus.CREATED);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<Users> get(@RequestParam Long id){
        Users savedUser = userService.findById(id);
        return new ResponseEntity<Users>(savedUser,HttpStatus.OK);
    }
}
