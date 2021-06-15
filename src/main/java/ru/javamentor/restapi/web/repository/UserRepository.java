package ru.javamentor.restapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javamentor.restapi.web.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u  JOIN FETCH u.roles AS roles WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
}
