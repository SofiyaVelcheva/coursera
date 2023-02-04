package coursera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBManager {
    INSTANCE;

    private Connection connection;
    private String ip = "127.0.0.1";
    private String port = "3306";
    private String schema = "coursera";
    private String user = "root";
    private String pass = "root";

    DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + schema, user, pass);
            System.out.println("Connection created");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection creation failed - " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("connection could not be closed" + e.getMessage());
        }
    }
}
