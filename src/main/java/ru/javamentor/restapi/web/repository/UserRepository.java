package ru.javamentor.restapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.restapi.web.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u  inner JOIN FETCH u.roles as roles WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
    //User findUserByEmail(String email);
}
