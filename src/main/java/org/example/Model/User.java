package org.example.Model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
@Getter
@Setter
public class User {
    PreparedStatement preparedStatement = null;

    Scanner readLine = new Scanner(System.in);
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String userName;
    protected String password;

    protected Integer departamentId;

    protected void enteringIntoDataBase(@NotNull Connection connection) throws SQLException {
        String INSERT_NEW = "INSERT INTO users (FirstName, LastName, Email, UserName, Password, Department)" +
                " VALUES(?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(INSERT_NEW);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, userName);
        preparedStatement.setString(5, password);
        preparedStatement.setInt(6, this.departamentId);

        preparedStatement.execute();
    }
    public void Registration(Connection connection) throws SQLException {
        System.out.println("Введите имя: ");
        this.firstName = readLine.next();

        System.out.println("Введите фамилию: ");
        this.lastName = readLine.next();

        System.out.println("Введите email: ");
        this.email = readLine.next();

        System.out.println("Введите логин: ");
        this.userName = readLine.next();

        System.out.println("Введите пароль: ");
        this.password = readLine.next();

        enteringIntoDataBase(connection);
    }

}
