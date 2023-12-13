package wt3.data_access.dao.impl;

import wt3.data_access.dao.AuthDao;
import org.springframework.dao.EmptyResultDataAccessException;
import wt3.data_access.dto.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;


@Component
public class JDBCAuthDao implements AuthDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User loginUser(String email, String password) {
        try {
            User user = jdbcTemplate.queryForObject(
                    "select * from users where email = ?",
                    new Object[]{email},
                    new BeanPropertyRowMapper<>(User.class)
            );
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new Exception("Incorrect email or password");
            }
            return user;
        } catch (Exception e) {
            if (e instanceof EmptyResultDataAccessException) {
                throw new RuntimeException("Incorrect email or password");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void registerUser(String name, String email, String password) {
        try {
            int rowsAffected = jdbcTemplate.update(
                    "INSERT INTO users(name, email, password) VALUES (?, ?, ?)",
                    name, email, password
            );
        } catch (Exception e) {
            throw new RuntimeException("Email address is already used");
        }
    }
}
