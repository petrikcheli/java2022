package org.example.payment;


import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.text.ParseException;
import java.util.Calendar;

import java.text.SimpleDateFormat;

@Getter
@Setter
public class indebtedness {
    protected PreparedStatement preparedStatement;
    protected Integer idParents;
    protected Integer money;
    protected Integer ActiveMode; // 1 - задолженость актуальна; 2 - задолженость не актуальна; 3 - отчет по задолженности отправлен бухгалетру; 4 - отчет принят бухгалтером; 5 - задача отправлена call-center 6 - ученик отчислен


    public static int[] Time(int countDay){
        int[] resultDate = new int[3];
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, countDay);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        resultDate[0] = Integer.parseInt(year);
        resultDate[1] = Integer.parseInt(month);
        resultDate[2] = Integer.parseInt(day);
        return resultDate;
    }

    public static void setDept(int idparents, Connection connection) throws ParseException, SQLException {
        PreparedStatement preparedStatement1;
        int[] dateArray = Time(0);
        Date date = new Date(dateArray[0] - 1900, dateArray[1], dateArray[2]);

        String INSERT_indebtedness_NEW = "INSERT INTO indebtedness (idParents, money, ActiveMode, date)" +
                " VALUES(?, ?, ?, ?)";
        preparedStatement1 = connection.prepareStatement(INSERT_indebtedness_NEW);
        preparedStatement1.setInt(1, idparents);
        preparedStatement1.setInt(2, 4000);
        preparedStatement1.setInt(3, 1);
        preparedStatement1.setDate(4, date);

        preparedStatement1.execute();

    }
    public static void RemoveDept(int idparents, Connection connection, Date paymentDay) throws SQLException {
        PreparedStatement preparedStatement;
        String INSERT_finance_NEW = "INSERT INTO finance (idParents, money, date)" +
                " VALUES(?, ?, ?)";
        preparedStatement = connection.prepareStatement(INSERT_finance_NEW);
        preparedStatement.setInt(1, idparents);
        preparedStatement.setInt(2, 4000);
        preparedStatement.setDate(3, paymentDay);
        preparedStatement.execute(); //отправка платежа в базу данных finance

        String DELETE_NEW = "DELETE FROM indebtedness WHERE idParents = '" +idparents+ "' ";
        preparedStatement.executeUpdate(DELETE_NEW); // удаление записи об задолжености

    }
    public void dataUpdate(Connection connection) throws SQLException, ParseException {
        String SELECT_NEW = "SELECT * FROM indebtedness";
        preparedStatement = connection.prepareStatement(SELECT_NEW);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){

            int activeModeSql = resultSet.getInt(4);
            if(activeModeSql == 3 || activeModeSql == 4) continue;
            java.sql.Date deptDate = resultSet.getDate(5);
            int idindebtedness = resultSet.getInt(1);
            int idparen = resultSet.getInt(2);
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = simpleDate.parse(resultSet.getDate(5).toString());

            Calendar thirdDayCalendar =  Calendar.getInstance();

            Calendar tenthDayCalendar = Calendar.getInstance();

            tenthDayCalendar.setTime(date);
            thirdDayCalendar.setTime(date);

            thirdDayCalendar.add(Calendar.DAY_OF_MONTH, 2);

            tenthDayCalendar.add(Calendar.DAY_OF_MONTH, 9);

            int day = Integer.parseInt(String.valueOf(tenthDayCalendar.get(Calendar.DAY_OF_MONTH)));
            System.out.println(day);

            int[] nowDateTest = Time(0);

            int thirdDayInt = Integer.parseInt(String.valueOf(thirdDayCalendar.get(Calendar.DAY_OF_MONTH)));
            int thirdMonthInt = Integer.parseInt(String.valueOf(thirdDayCalendar.get(Calendar.MONTH)));
            int thirdYearInt = Integer.parseInt(String.valueOf(thirdDayCalendar.get(Calendar.YEAR)));
            java.sql.Date thirdDaySqlDate = new java.sql.Date(thirdYearInt - 1900, thirdMonthInt, thirdDayInt);

            int tenthDayInt = Integer.parseInt(String.valueOf(tenthDayCalendar.get(Calendar.DAY_OF_MONTH)));
            int tenthMonthInt = Integer.parseInt(String.valueOf(tenthDayCalendar.get(Calendar.MONTH)));
            int tenthYearInt = Integer.parseInt(String.valueOf(tenthDayCalendar.get(Calendar.YEAR)));
            java.sql.Date tenthDaySqlDate = new java.sql.Date(tenthYearInt - 1900, tenthMonthInt, tenthDayInt);

            int newDayInt = nowDateTest[2];
            int newMonthInt = nowDateTest[1];
            int newYearInt = nowDateTest[0];
            java.sql.Date nowDaySqlDate = new java.sql.Date(newYearInt - 1900, newMonthInt, newDayInt);

            boolean tri = thirdDaySqlDate.after(deptDate) && nowDaySqlDate.after(thirdDaySqlDate);
            boolean ten = tenthDaySqlDate.after(nowDaySqlDate);
            PreparedStatement preparedStatement1;
            if(tri && ten){
                preparedStatement1 = connection.prepareStatement("UPDATE indebtedness  SET ActiveMode = 3 WHERE idIndebtedness = '" +idindebtedness+ "' ");
                preparedStatement1.executeUpdate();
                // отправляет запрос бухгалтеру, чтобы передать кол центру задачу
            }
            if(tenthDaySqlDate.after(deptDate)){
                System.out.println("отчислен");
                // отчисляет ученика
            }

        }
    }
}
