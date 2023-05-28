package org.example.client;

import org.example.Model.User;

import java.sql.*;
import java.util.Scanner;

import static org.example.payment.indebtedness.*;


public class Parents extends User{
    public Parents(){super.departamentId = 5;}

    public void debtPayment(Parents parents, Connection connection, Date nowDate) throws SQLException {
        int idParent = parents.getId(connection);
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE indebtedness SET ActiveMode = 2 WHERE idParents = '"+idParent+"'");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("INSERT INTO finance (IDPARENTS, MONEY, DATE)" +
                " VALUES(?, ?, ?)");
        preparedStatement.setInt(1, idParent);
        preparedStatement.setInt(2, 4000);
        preparedStatement.setDate(3, nowDate);
        preparedStatement.execute();


    }
    public void ParentsMain(Parents parents, Connection connection) throws SQLException {
        Scanner readLine = new Scanner(System.in);
        int mode = 0;
        int money = 0;
        int idParent = parents.getId(connection);
        int[] tempDateArray = Time(0);
        java.sql.Date nowDate = new java.sql.Date(tempDateArray[0] - 1900, tempDateArray[1], tempDateArray[2]);
        PreparedStatement preparedStatement1;
        preparedStatement1 = connection.prepareStatement("SELECT money, ActiveMode FROM indebtedness WHERE idParents = '"+idParent+"'");
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while(resultSet1.next()){
            money = resultSet1.getInt(1);
            mode = resultSet1.getInt(2);
        }
        System.out.println("Здравствуйте " + parents.firstName);
        if(mode == 6){
            System.out.println("Вы отчислены за неуплату");
            System.out.println("Нажмите 1, чтобы выйти");
            int action2 = 0;
            while(action2 != 1){
                action2 = readLine.nextInt();
            }
            return;
        }
        System.out.println("1 - финансы");
        int action0 = readLine.nextInt();
        while(action0 != 0) {
            switch (action0) {
                case 1:
                    int money1 = 0;
                    PreparedStatement preparedStatement;
                    preparedStatement = connection.prepareStatement("SELECT money FROM indebtedness WHERE idParents = '" + idParent + "'");
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        money1 = resultSet.getInt(1);
                    }
                    if (money1 != 0) {
                        System.out.println("У вас имеется задолженость в сумме: " + money1 + " рублей");
                        System.out.println("1 - чтобы оплатить");
                        System.out.println("0 - чтобы выйти");
                        int action3 = readLine.nextInt();
                        switch (action3){
                            case 1:
                                RemoveDept(idParent, connection, nowDate);
                                action0 = 0;
                                break;
                            case 0:
                                action0 = 0;
                                break;
                        }
                    } else {
                        System.out.println("У вас нет задолженостей");
                        System.out.println("0 - чтобы выйти");
                        action0 = readLine.nextInt();
                        break;
                    }
                case 0:
                    action0 = 0;
                    break;
            }
        }
    }
}
