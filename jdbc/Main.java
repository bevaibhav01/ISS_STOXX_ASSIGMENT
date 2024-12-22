package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/college";
    private static final String username = "root";
    private static final String password = "test123";
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establish a connection
            connection = DriverManager.getConnection(jdbcURL, username, password);
            logger.info("Connected to the database!");

            // Perform CRUD operations
            createStudent(connection, "David Lee", 23, "david@example.com", "Engineering", "2023-09-01");
            readStudents(connection);
            updateStudentMajor(connection, 1, "Data Science"); // Update major for student with ID 1
            deleteStudent(connection, 3); // Delete student with ID 3
            readStudents(connection);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while connecting to the database or performing operations.", e);
        } finally {
            // Close the connection
            closeConnection(connection);
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
            logger.info("Student added successfully: " + name);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to insert student: " + name, e);
        }
    }

    // Read: Retrieve all students
    public static void readStudents(Connection connection) {
        String selectQuery = "SELECT * FROM student";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            logger.info("Fetching student records...");
            while (resultSet.next()) {
                logger.info("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Age: " + resultSet.getInt("age") +
                        ", Email: " + resultSet.getString("email") +
                        ", Major: " + resultSet.getString("major") +
                        ", Enrollment Date: " + resultSet.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch student records.", e);
        }
    }

    // Update: Update a student's major
    public static void updateStudentMajor(Connection connection, int id, String newMajor) {
        String updateQuery = "UPDATE student SET major = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newMajor);
            preparedStatement.setInt(2, id);
            int rowsAffected = preparedStatement.executeUpdate();
            logger.info(rowsAffected + " student(s) updated successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update student with ID: " + id, e);
        }
    }

    // Delete: Remove a student by ID
    public static void deleteStudent(Connection connection, int id) {
        String deleteQuery = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            logger.info(rowsAffected + " student(s) deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete student with ID: " + id, e);
        }
    }

    // Utility method to close the connection
    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed.");
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed to close the database connection.", e);
            }
        }
    }
}
