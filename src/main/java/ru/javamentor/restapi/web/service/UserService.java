package ru.javamentor.restapi.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javamentor.restapi.web.model.dto.UserDTO;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    void saveUser(UserDTO user);
    void deleteUserById(Long id);
    UserDTO loadPrincipalDTO(String email);
}

