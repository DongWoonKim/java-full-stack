package com.example.tobi.springtobi.ch03.ex_3_6.dao;

import com.example.tobi.springtobi.ch03.ex_3_6.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao_v2 {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper;

    public UserDao_v2(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));

                return null;
            }
        };
    }

    public void add(User user) {
        this.jdbcTemplate.update(
                "INSERT INTO users(id, name, password) VALUES(?, ?, ?)",
                user.getId(), user.getName(), user.getPassword()
        );
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, this.rowMapper);
    }

    public User get(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this.rowMapper);
    }

    public int getCount() {
        String sql = "SELECT COUNT(*) FROM users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void deleteAll() {
        this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        return con.prepareStatement("DELETE FROM users");
                    }
                }
        );
    }

}
