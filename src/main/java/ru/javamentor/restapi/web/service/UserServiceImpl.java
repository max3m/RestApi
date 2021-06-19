package ru.javamentor.restapi.web.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javamentor.restapi.web.model.dto.UserConverter;
import ru.javamentor.restapi.web.model.dto.UserDTO;
import ru.javamentor.restapi.web.repository.UserRepository;
import ru.javamentor.restapi.web.model.User;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConverter = userConverter;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException(String.format("Пользователь с почтой %s не найден", email));
        }
        return user;
    }

    public UserDTO loadPrincipalDTO(String email){
        return userConverter.convertUserToUserDTO(loadUserByUsername(email));
    }

    @Override
    public Set<UserDTO> getAllUsers() {
        return userConverter.convertAllToDTO(userRepository.findAll());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.getById(id);
        if(user == null){
            throw new UsernameNotFoundException(String.format("Пользователь с id = %s не найден", id));
        }
        user.setPassword("");
        return userConverter.convertUserToUserDTO(user);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        if (userDTO.getRoles().length == 0) {
            userDTO.setRoles(new String[]{"USER"});
        }
        User user = userConverter.convertUserDTOToUser(userDTO);
        if(userDTO.getPassword() == "") {
            user.setPassword(loadUserByUsername(userDTO.getEmail()).getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
