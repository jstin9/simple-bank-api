package com.jstn9.simplebankapi.controllers;

import com.jstn9.simplebankapi.dtos.UserRequestDTO;
import com.jstn9.simplebankapi.dtos.UserResponseDTO;
import com.jstn9.simplebankapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponseDTO> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.saveUser(userRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> partialUpdateUser(@PathVariable UUID id, @Valid @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.partialUpdateUser(id, userRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.updateUser(id, userRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
