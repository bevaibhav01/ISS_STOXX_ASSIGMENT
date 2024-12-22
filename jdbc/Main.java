package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "testpassword";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Connected to the database!");

            // Perform CRUD operations
            createStudent(connection, "Vaibhav Mahale", 23, "vaibhav@example.com", "Engineering", "2023-09-01");
            readStudents(connection);
            updateStudentMajor(connection, 1, "Data Science"); // Update major for student with ID 1
            deleteStudent(connection, 3); // Delete student with ID 3
            readStudents(connection);
            closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create: Insert a new student
    public static void createStudent(Connection connection, String name, int age, String email, String major, String enrollmentDate) {
        String insertQuery = "INSERT INTO student (name, age, email, major, enrollment_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, major);
            preparedStatement.setString(5, enrollmentDate);
            preparedStatement.executeUpdate();
            System.out.println("Student added successfully: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read: Retrieve all students
    public static void readStudents(Connection connection) {
        String selectQuery = "SELECT * FROM student";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            System.out.println("Student Records:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Age: " + resultSet.getInt("age") +
                        ", Email: " + resultSet.getString("email") +
                        ", Major: " + resultSet.getString("major") +
                        ", Enrollment Date: " + resultSet.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update: Update a student's major
    public static void updateStudentMajor(Connection connection, int id, String newMajor) {
        String updateQuery = "UPDATE student SET major = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newMajor);
            preparedStatement.setInt(2, id);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " student(s) updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete: Remove a student by ID
    public static void deleteStudent(Connection connection, int id) {
        String deleteQuery = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " student(s) deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     // Utility method to close the connection
     private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
