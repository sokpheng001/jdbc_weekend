package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connection(){
        try{
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/java_weekend",
                    "postgres",
                    "123");
        }catch (Exception exception){
            System.out.println("Problem during connecting to database: "
                    + exception.getMessage());
        }
        return null;
    }
}
