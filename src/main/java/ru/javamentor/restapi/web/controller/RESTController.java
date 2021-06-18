package ru.javamentor.restapi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.restapi.web.model.dto.UserDTO;
import ru.javamentor.restapi.web.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {
    private UserService userService;

    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO userById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addNewUser(@RequestBody UserDTO user){
        userService.saveUser(user);
        return user;
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody UserDTO user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/principal")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getPrincipal() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.loadPrincipalDTO(userDetails.getUsername());
    }
}
