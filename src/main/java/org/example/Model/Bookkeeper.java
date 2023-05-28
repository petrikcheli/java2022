package org.example.Model;

import org.apache.poi.hssf.record.chart.LineFormatRecord;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.logging.Logger;
import org.example.payment.indebtedness;


public class Bookkeeper extends User{
    protected indebtedness indebtedness = new indebtedness();
    protected PreparedStatement preparedStatement;
    protected PreparedStatement prepareStatement2 = null;
    public List<Integer> requestList = new ArrayList<Integer>();

    Scanner readLine = new Scanner(System.in);
    Logger logger = Logger.getLogger(Bookkeeper.class.getName());
    public Bookkeeper(){super.departamentId = 6;}

    public void fillingListRequest(Connection connection) throws SQLException { // заполнение листа должниками
        preparedStatement = connection.prepareStatement("SELECT idParents FROM indebtedness WHERE ActiveMode = 3 OR ActiveMode = 4");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            requestList.add(resultSet.getInt(1));
        }
        for (int item :
                requestList) {
            preparedStatement = connection.prepareStatement("UPDATE indebtedness SET ActiveMode = 4 WHERE idIndebtedness = '" + item + "'  ");
            preparedStatement.executeUpdate();
        }
        System.out.println("Выберите должника, чтобы отправить задачу call-centerу ");
        System.out.println("чтобы выйти нажмите - 0");
        for (int i = 0; i < requestList.size(); i++){
            int iduser = requestList.get(i);
            preparedStatement = connection.prepareStatement("SELECT FirstName, LastName FROM users WHERE idUsers = '"+iduser+"'");
            ResultSet infoAbotClient = preparedStatement.executeQuery();
            while(infoAbotClient.next()){
                String firstName = infoAbotClient.getString(1);
                String lastName = infoAbotClient.getString(2);
                System.out.println(i + 1 + " - "+firstName+" "+lastName);
            }
        }
        int action = readLine.nextInt();
        if (action == 0){
            System.out.println("До свидания!!");
        } else {
            System.out.println("1 - Отправить задачу");
            System.out.println("0 - Отмена");
            int action1 = readLine.nextInt();
            switch(action1){
                case 1:
                    String firstNameClient = "";
                    String lastNameClient = "";
                    preparedStatement.executeUpdate("UPDATE indebtedness WHERE idParents = '" +requestList.get(action - 1)+ "' SET ActiveMode = 5 ");
                    ResultSet infoAboutClient = preparedStatement.executeQuery("SELECT FirstName, LastName FROM klient WHERE idKlient = '"+requestList.get(action - 1)+"'");
                    while(infoAboutClient.next()){
                        firstNameClient = infoAboutClient.getString(2);
                        lastNameClient = infoAboutClient.getString(3);
                    }
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Такого действия нет");
                    break;
            }
        }
    }
    public void requestForMain(){
    }

    public void bookkeeperMain(Bookkeeper bookkeeper, Connection connection) throws SQLException, ParseException {
        indebtedness.dataUpdate(connection);

        System.out.println("Привет Бухгалтер");
        System.out.println("1 - посмотреть должников");
        int action = readLine.nextInt();
        switch(action){
            case 0:
                break;
            case 1:
                fillingListRequest(connection);
                break;
        }

    }

}
