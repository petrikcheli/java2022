package org.example;

import org.example.JDBC.*;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.Entrance.Authentication.authenticationUser;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        Logger logger = Logger.getLogger(Main.class.getName());
        Scanner readLine = new Scanner(System.in);

        ConnectionBD getConnect = new ConnectionBD();
        Connection connection = getConnect.connectionBD();

        StatementBD getStatament = new StatementBD();
        Statement statement = getStatament.statementBD(connection);

        int action = 0;
        while (action != 3){
            action = 0;
            System.out.println("1 - войти");
            System.out.println("3 - exit");
            action = readLine.nextInt();
            switch(action) {
                case 1:
                    authenticationUser(connection);
                    break;
                case 3:
                    break;
                default:
                    logger.warning("Такого действия нет ");
            }
        }
        connection.close();
    }
}