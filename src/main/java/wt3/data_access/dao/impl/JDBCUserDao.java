package wt3.data_access.dao.impl;

import wt3.data_access.dao.UserDao;
import wt3.data_access.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JDBCUserDao implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUsers() {
        try {
            return jdbcTemplate.query(
                    "select * from users WHERE role = 'client' ORDER BY banned ASC, rating DESC",
                    new BeanPropertyRowMapper<>(User.class)
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateUser(int id, int rating, boolean isBanned) {
        try {
            jdbcTemplate.update(
                    "UPDATE users SET rating = ?, banned = ? WHERE (id = ?)",
                    rating, isBanned, id
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
