package org.example.Model;

import org.example.Main;
import org.example.klient.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class CCenter extends User{
    public CCenter(){super.departamentId = 4;}
    public void CCenterMain(CCenter center, Connection connection) throws SQLException {

        Logger logger = Logger.getLogger(CCenter.class.getName());
        Scanner readline = new Scanner(System.in);
        int action1 = 0;
        System.out.println("Привет Call-Center");

        logger.info("1 - зарегистрировать нового клиента");
        logger.info("3 - выйти");
        action1 = readline.nextInt();
        while(action1 != 3){
            action1 = 0;
            switch(action1){
                case 1:
                    Parents parents = new Parents();
                    parents.Registration(connection);

                    int idparets = parents.getId();

                    child child = new child();
                    child.Registration(connection, idparets);
                    break;
                case 3:
                    action1 = 3;
                    break;
            }
        }
    }
}
