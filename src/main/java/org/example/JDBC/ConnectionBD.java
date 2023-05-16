package org.example.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private String url = "jdbc:mysql://localhost:3306/java2023";
    private String user = "root";
    private String password = "root";

    protected Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Connection connectionBD(){
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Успешное подключение к базе данных");
        } catch(SQLException e){
            System.out.println("Сбой подключения к базе данных");
            throw new RuntimeException(e);
        }
        return connection;
    }

}
