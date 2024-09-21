package com.example.tobi.springtobi.ch03.ex_3_2.dao;

import com.example.tobi.springtobi.ch03.ex_3_2.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao_v1 {

    private DataSource dataSource;


    public UserDao_v1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into users(id, name, password) values(?,?,?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");

        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

    public int getCount() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();

            ps = connection.prepareStatement("SELECT COUNT(*) FROM users");

            rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if ( rs != null ) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if ( ps != null ) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if ( connection != null ) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void deleteAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            // 1. 메서드 추출방식...
//            ps = makePreparedStatement(connection);

            // 2. 전략 패턴 적용
            // - 한계점
            // 전략 패턴은 필요에 따라 컨텍스트는 그대로 유지되면서 전략을바꿔 쓸 수 있다는 것인데,
            // 이렇게 컨텍스트 안에서 이미 구체적인 클래스인 DeleteAllStatement를 사용하도록 고정되어 있는 것이 문제다.
            StatementStrategy strategy = new DeleteAllStatement();
            ps = strategy.makePreparedStatement(connection);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

    private PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ps = conn.prepareStatement("DELETE FROM users");

        return ps;
    }

}
