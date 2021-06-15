# -- Table: users
CREATE TABLE users(
                      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(30) UNIQUE,
                      password VARCHAR(80),
                      firstname VARCHAR(50),
                      lastname VARCHAR(50),
                      age INT,
                      email VARCHAR(50) UNIQUE

)
    ENGINE  = InnoDB;



-- Table: roles
CREATE TABLE roles (
                       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       rolename VARCHAR(50)
)
    ENGINE  = InnoDB;


-- Table: for mapping user add roles: user_roles
CREATE TABLE  user_roles (
                             user_id BIGINT,
                             role_id BIGINT,

                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (role_id) REFERENCES roles(id),

                             UNIQUE (user_id, role_id)

)
    ENGINE  = InnoDB;
