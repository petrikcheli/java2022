package org.example.Entrance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.Entrance.Initialization.InitializationUser;

public class Authentication {

    public static void AuthenticationUser(Connection connection) throws SQLException {
        Scanner readLine = new Scanner(System.in);
        PreparedStatement preparedStatement;
        Boolean SuccessfulLogin = false;
        String UserNameRead;
        String PasswordRead;
        String UserNameResualSet = null;
        String PasswordResualSet = null;
        Integer IdUserResualSet = null;

        System.out.println("Введите логин: ");
        UserNameRead = readLine.next();
        System.out.println("Введите пароль: ");
        PasswordRead = readLine.next();

        String CHECK_NEW = "SELECT  UserName, Password, idUsers FROM users WHERE UserName = '" +UserNameRead+ "' ";
        preparedStatement = connection.prepareStatement(CHECK_NEW);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            UserNameResualSet = resultSet.getString(1);
            PasswordResualSet = resultSet.getString(2);
            IdUserResualSet = resultSet.getInt(3);
        }

        if(UserNameResualSet != null){
            if(PasswordResualSet.equals(PasswordRead)){
                System.out.println("Удачный вход!");
                SuccessfulLogin = true;
                InitializationUser(IdUserResualSet, connection);
            } else{
                System.out.println("Вы ввели неправильный пароль");
                SuccessfulLogin = false;
            }
        } else{
            System.out.println("Вы ввели неправильный логин");
            SuccessfulLogin = false;
        }

    }
}
