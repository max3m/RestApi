package ru.javamentor.restapi.web.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String lastname;
    private Byte age;
    private String email;
    private String [] roles;
    private String password;
}
