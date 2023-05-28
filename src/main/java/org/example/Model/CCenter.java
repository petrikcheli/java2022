package org.example.Model;

import org.example.payment.indebtedness.*;
import org.example.client.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.example.payment.indebtedness.setDept;

public class CCenter extends User{
    public CCenter(){super.departamentId = 4;}
    public void tasks(){

    }
    public void CCenterMain(CCenter center, Connection connection) throws SQLException, ParseException {

        Logger logger = Logger.getLogger(CCenter.class.getName());
        Scanner readline = new Scanner(System.in);
        int action1 = 0;
        System.out.println("Привет Call-Center");

        System.out.println("1 - зарегистрировать нового клиента");
        System.out.println("3 - выйти");
        action1 = readline.nextInt();
        while(action1 != 3){
            switch(action1){
                case 1:
                    Parents parents = new Parents();
                    parents.Registration(connection);


                    int idparets = parents.getId(connection);

                    child child = new child();
                    child.registration(connection, idparets);
                    setDept(idparets, connection);
                    break;
                case 3:
                    action1 = 3;
                    break;
            }
            action1 = 0;
        }
    }
}
