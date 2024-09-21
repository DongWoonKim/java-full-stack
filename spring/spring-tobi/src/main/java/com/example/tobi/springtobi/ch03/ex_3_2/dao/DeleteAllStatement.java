package com.example.tobi.springtobi.ch03.ex_3_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {
    @Override
    public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ps = conn.prepareStatement("DELETE FROM users");

        return ps;
    }
}
