import java.sql.*;

/*
CREATE DATABASE java_basic
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
 */

public class DatabaseSample {

    public Connection conn() throws ClassNotFoundException, SQLException {
        String URL = "jdbc:mysql://localhost:3306/tobi"; // 데이터베이스 URL
        String USER = "root"; // 데이터베이스 사용자 이름
        String PASSWORD = "DongunKim91"; // 데이터베이스 비밀번호

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println("Conn Successful!");
        return connection;
    }

    public void insertData(String name, int age) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO your_table_name (name, age) VALUES (?, ?)";

        try (Connection connection = conn();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new record was inserted successfully!");
            }
        }
    }

    public void updateData(int id, String name, int age) throws ClassNotFoundException, SQLException {
        String query = "UPDATE your_table_name SET name = ?, age = ? WHERE id = ?";

        try (Connection connection = conn();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing record was updated successfully!");
            }
        }
    }

    public void deleteData(int id) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM your_table_name WHERE id = ?";

        try (Connection connection = conn();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A record was deleted successfully!");
            }
        }
    }

    public void selectOne(int id) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM your_table_name WHERE id = ?";

        try (Connection connection = conn();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");

                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                } else {
                    System.out.println("No record found with ID: " + id);
                }
            }
        }
    }

    public void selectList() throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM your_table_name";

        try (Connection connection = conn();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("--------------------");
            }
        }
    }

    public static void main(String[] args) {
        DatabaseSample databaseSample = new DatabaseSample();
        try {
            databaseSample.conn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
