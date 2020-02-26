package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    public static Connection createConnection(){
        Connection connection = null;
        String url="jdbc:postgresql://localhost:5432/postgres"; // postgresql URl including database name
        String name="postgres"; // postgresql login name
        String pass="1321"; // postgresql login password

        try{
            try{
                Class.forName("org.postgresql.Driver"); // load database drivers
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            connection = DriverManager.getConnection(url, name, pass); // try connecting to the database

        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }
}
