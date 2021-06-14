package ru.javamentor.restapi.web.model.dto;

import org.springframework.stereotype.Component;
import ru.javamentor.restapi.web.model.Role;
import ru.javamentor.restapi.web.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    public UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        /*String [] roles = new String[user.getRoles().size()];
        for(int i = 0; i < user.getRoles().size(); i++){
            roles[i] = user.getRoles().get(i).toString();
        }*/
        String[] roles = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList())
                .toArray(String[]::new);
        userDTO.setRoles(roles);
        return userDTO;
    }

    public List<UserDTO> convertAllToDTO(List<User> userList){
        return userList.stream()
                .map(this::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

    public User convertUserDTOToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstname(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
//        List<Role> roles = new ArrayList<>();
//        for(String roleName : userDTO.getRoles()) { roles.add(new Role(roleName)); }
        List<Role> roles = Arrays.stream(userDTO.getRoles())
                .map(role->new Role(role))
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