package org.example.klient;

import org.example.Model.User;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

@Getter
@Setter
public class child {
    Logger logger = Logger.getLogger(child.class.getName());
    Scanner readLine = new Scanner(System.in);
    PreparedStatement preparedStatement = null;
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected Integer idParents;
    protected Integer ActiveMode;
    protected Integer departamentId;
    public child(){this.departamentId = 6;}
    public void Registration(Connection connection, int idparents) throws SQLException {
        logger.info("Введите имя: ");
        this.firstName = readLine.next();

        logger.info("Введите фамилию: ");
        this.lastName = readLine.next();

        this.idParents = idparents;

        this.ActiveMode = 1;

        enteringIntoDataBase(connection);
    }
    protected void enteringIntoDataBase(@NotNull Connection connection) throws SQLException {
        String INSERT_NEW = "INSERT INTO child (FirstName, LastName, idParents, ActiveMode, idDepartament)" +
                " VALUES(?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(INSERT_NEW);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setInt(3, idParents);
        preparedStatement.setInt(4, ActiveMode);
        preparedStatement.setInt(5, departamentId);
        preparedStatement.execute();
    }
}
