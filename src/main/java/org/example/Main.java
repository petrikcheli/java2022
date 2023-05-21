package org.example;

import org.example.JDBC.*;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.Entrance.Authentication.AuthenticationUser;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        Logger logger = Logger.getLogger(Main.class.getName());
        Scanner readLine = new Scanner(System.in);

        ConnectionBD getConnect = new ConnectionBD();
        Connection connection = getConnect.connectionBD();

        StatementBD getStatament = new StatementBD();
        Statement statement = getStatament.statementBD(connection);

        int action = 0;
        while (action != 3){
            action = 0;
            logger.info("1 - войти");
            logger.info("3 - exit");
            action = readLine.nextInt();
            switch(action) {
                case 1:
                    AuthenticationUser(connection);
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