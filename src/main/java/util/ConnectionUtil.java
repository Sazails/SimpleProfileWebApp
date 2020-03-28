package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String driverName = "org.postgresql.Driver";
    private static final String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "1321";

    private Connection connection = null;

    public Connection createConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driverName);
        connection = DriverManager.getConnection(connectionUrl, username, password);
        setConnection(connection);
        return getConnection();
    }

    public static Connection createTempConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driverName);
        return DriverManager.getConnection(connectionUrl, username, password);
    }

    public void setConnection(Connection otherConnection){
        this.connection = otherConnection;
    }

    public void closeConnection() throws SQLException{
        this.connection.close();
    }

    public Connection getConnection(){
        return connection;
    }
}
