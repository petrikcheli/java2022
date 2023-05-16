package org.example.Model;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends User{
    public static void OpenExel(){
        try
        {
//constructor of file class having file as argument
            File file = new File("C:\\Users\\patem\\Desktop\\il.xlsx");
            if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())         //checks file exists or not
                desktop.open(file);              //opens the specified file
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    Scanner readLine = new Scanner(System.in);
    public Admin(){super.departamentId = 1;}
    int action = 0;
    int action2 = 0;
    public void AdminMain(Admin admin, Connection connection) throws SQLException {
        while(action != 3) {
            action = 0;
            action2 = 0;
            System.out.println("Привет Админ");
            System.out.println("Выберите действие: ");
            System.out.println("1 - Создать нового работника");
            System.out.println("2 - Посмотреть расписание");
            System.out.println("3 - выйти");
            action = readLine.nextInt();
            switch(action) {
                case 1 -> {
                    System.out.println("1 - Зарегистрировать кол-центр");
                    System.out.println("2 - Зарегистрировать учителя");
                    System.out.println("3 - Зарегистрировать тех. поддержку");
                    action2 = readLine.nextInt();
                    switch(action2) {
                        case 1 -> {
                            CCenter cCenter = new CCenter();
                            cCenter.Registration(connection);
                        }
                        case 2 -> {
                            Teacher teacher = new Teacher();
                            teacher.Registration(connection);
                        }
                        case 3 -> {
                            TechSupport techSupport = new TechSupport();
                            techSupport.Registration(connection);
                        }
                        default -> System.out.println("Такого действия нет");
                    }
                }
                case 2 -> OpenExel();
                case 3 -> System.out.println("Досвидание!!");
                default -> System.out.println("Такого действия нет ");
            }
        }
    }
}
