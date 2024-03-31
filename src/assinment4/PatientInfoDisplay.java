package assinment4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientInfoDisplay {
    // Database URL, username, and password
    private static final String DATABASE_URL = "jdbc:oracle:thin:@//localhost:1521/xe"; // Change as per your Oracle connection details
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            // Prepare SQL query to select patient information
            String query = "SELECT id, name, problem, bill FROM patients";
            statement = connection.prepareStatement(query);

            // Execute the query
            resultSet = statement.executeQuery();

            // Display patient information
            System.out.println("Patient Information:");
            System.out.printf("%-5s %-20s %-30s %-10s\n", "ID", "Name", "Problem", "Bill");
            System.out.println("--------------------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String problem = resultSet.getString("problem");
                double bill = resultSet.getDouble("bill");

                System.out.printf("%-5d %-20s %-30s %-10.2f\n", id, name, problem, bill);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connections
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
s

