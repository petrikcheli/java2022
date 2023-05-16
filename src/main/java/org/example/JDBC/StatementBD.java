package org.example.JDBC;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementBD {
    protected PreparedStatement preparedStatement;
    protected Statement statement;

    public Statement getStatement() {
        return statement;
    }

    public Statement statementBD(Connection connection){
        try {
            statement = connection.createStatement();
            System.out.println("Успешное подключение к statement");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

}
