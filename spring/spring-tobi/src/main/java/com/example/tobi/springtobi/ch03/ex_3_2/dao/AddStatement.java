package com.example.tobi.springtobi.ch03.ex_3_2.dao;

import com.example.tobi.springtobi.ch03.ex_3_2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy{

    private User user;

    public AddStatement(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("insert into users(id, name, password) values(?,?,?)");

        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        return preparedStatement;
    }
}
