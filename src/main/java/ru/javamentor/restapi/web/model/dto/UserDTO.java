package ru.javamentor.restapi.web.model.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@Data
public class UserDTO implements Comparable<UserDTO> {
    private Long id;
    private String name;
    private String lastname;
    private Byte age;
    private String email;
    private String [] roles;
    private String password;

    @Override
    public int compareTo(@NotNull UserDTO userDTO) {
        return id.compareTo(userDTO.getId());
    }
}
