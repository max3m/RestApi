package ru.javamentor.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javamentor.restapi.web.model.User;
import ru.javamentor.restapi.web.model.dto.UserDTO;
import ru.javamentor.restapi.web.service.UserService;

@SpringBootApplication
public class RestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
