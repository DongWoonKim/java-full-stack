package notice;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {

    private Connection getConnection() {
        String URL = "jdbc:mysql://localhost:3306/notice"; // 데이터베이스 URL
        String USER = "root"; // 데이터베이스 사용자 이름
        String PASSWORD = "DongunKim91"; // 데이터베이스 비밀번호

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContentExc(String userId) {
        String query = "DELETE FROM content WHERE user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean leaveExc(String userId) {
        String query = "DELETE FROM user WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUpExc(String userId, String password, String userName) {
        String query = "insert into user (user_id, password, name) values(?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userName);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("회원가입이 성공적으로 완료되었습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SignInResponseDTO signInExc(String userId, String password) {
        String query = "SELECT name, password FROM user WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            // 결과 가져오기
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String getPw = resultSet.getString("password");
                if (!password.equals(getPw)) return null;

                return new SignInResponseDTO(true, name);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkUserId(String userId) {
        String query = "SELECT count(*) FROM user WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            // 쿼리 실행
            ResultSet resultSet = preparedStatement.executeQuery();

            // 결과 가져오기
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0 ? true : false;
            }

            throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean newNotice(String userId, String content) {
        String query = "insert into content (user_id, content) values(?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, content);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getList() {
        String query = "SELECT id, user_id, content, created FROM content";
        List<String> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userId = resultSet.getString("user_id");
                String content = resultSet.getString("content");
                String created = resultSet.getString("created");

                String str = "[" + id + "] " + userId + " - " + content + " - " + created;
                list.add(str);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getListByUserId(String userId) {
        String query = "SELECT id, user_id, content, created FROM content WHERE user_id = ?";
        List<String> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                String created = resultSet.getString("created");

                String str = "[" + id + "] " + userId + " - " + content + " - " + created;
                list.add(str);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateNotice(int contentId, String content) {
        String query = "UPDATE content SET content = ?, created = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, content);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setInt(3, contentId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing record was updated successfully!");
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteNotice(int contentId) {
        String query = "DELETE FROM content WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, contentId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
