package ru.javamentor.restapi.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.javamentor.restapi.web.model.dto.UserConverter;
import ru.javamentor.restapi.web.model.dto.UserDTO;
import ru.javamentor.restapi.web.repository.RoleRepository;
import ru.javamentor.restapi.web.repository.UserRepository;
import ru.javamentor.restapi.web.model.Role;
import ru.javamentor.restapi.web.model.User;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
    }

    @Autowired
    public void setbCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public List<UserDTO> getAllUsers() {
        return userConverter.convertAllToDTO(userRepository.findAll());
    }

    @Override
    public UserDTO getUserById(Long id) {
        /*Optional<User> user =  userRepository.findById(id);
        if user.isPresent(){
        return userConverter.convertUserToUserDTO(user)};;*//*userRepository.findById(id);*/
        User user = userRepository.getById(id);
        if(user==null){
            throw new UsernameNotFoundException(String.format("Пользователь с id = %s не найден", id));
        }
        return userConverter.convertUserToUserDTO(user);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = userConverter.convertUserDTOToUser(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
