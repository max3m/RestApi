package ru.javamentor.restapi.web.model.dto;

import org.springframework.stereotype.Component;
import ru.javamentor.restapi.web.model.Role;
import ru.javamentor.restapi.web.model.User;
import ru.javamentor.restapi.web.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    private RoleRepository roleRepository;

    public UserConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        String[] roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList())
                .toArray(String[]::new);
        userDTO.setRoles(roles);
        return userDTO;
    }

    public Set<UserDTO> convertAllToDTO(List<User> userList){
        List<UserDTO> usersList = userList.stream()
                .map(this::convertUserToUserDTO)
                .collect(Collectors.toList());
        return new TreeSet<>(usersList);
    }

    public User convertUserDTOToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstname(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        List<Role> roles = Arrays.stream(userDTO.getRoles())
                .map(role->roleRepository.findRoleByRoleName(role))
                .collect(Collectors.toList());
        user.setRoles(roles);
        return user;
    }

    public List<User> convertAllToUser(List<UserDTO> userList){
        return userList.stream()
                .map(this::convertUserDTOToUser)
                .collect(Collectors.toList());
    }
}