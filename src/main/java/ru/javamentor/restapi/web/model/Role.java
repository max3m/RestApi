package ru.javamentor.restapi.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor

public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rolename")
    private String roleName;

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    @Override
    public String toString() {
        return roleName;
    }
}
