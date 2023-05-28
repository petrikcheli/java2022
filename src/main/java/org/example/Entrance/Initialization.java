package org.example.Entrance;

import org.example.Model.*;
import org.example.client.Parents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Initialization {

    private static void madeModelAndEntranceInMenu(User user, Connection connection) throws SQLException, ParseException {
        if(user.getDepartamentId() == 1){
            Admin admin = new Admin();

            admin.setId(user.getId());
            admin.setFirstName(user.getFirstName());
            admin.setLastName(user.getLastName());
            admin.setEmail(user.getEmail());
            admin.setUserName(user.getUserName());
            admin.setPassword(user.getPassword());

            admin.AdminMain(admin, connection);
        } else if (user.getDepartamentId() == 2) {
            Teacher teacher = new Teacher();

            teacher.setId(user.getId());
            teacher.setFirstName(user.getFirstName());
            teacher.setLastName(user.getLastName());
            teacher.setEmail(user.getEmail());
            teacher.setUserName(user.getUserName());
            teacher.setPassword(user.getPassword());

            teacher.TeacherMain(teacher);
        } else if (user.getDepartamentId() == 3) {
            TechSupport techSupport = new TechSupport();

            techSupport.setId(user.getId());
            techSupport.setFirstName(user.getFirstName());
            techSupport.setLastName(user.getLastName());
            techSupport.setEmail(user.getEmail());
            techSupport.setUserName(user.getUserName());
            techSupport.setPassword(user.getPassword());

            techSupport.TechSupportMain(techSupport);
        } else if (user.getDepartamentId() == 4) {
            CCenter centr = new CCenter();

            centr.setId(user.getId());
            centr.setFirstName(user.getFirstName());
            centr.setLastName(user.getLastName());
            centr.setEmail(user.getEmail());
            centr.setUserName(user.getUserName());
            centr.setPassword(user.getPassword());

            centr.CCenterMain(centr, connection);
        } else if (user.getDepartamentId() == 5) {
            Parents parents = new Parents();

            parents.setId(user.getId());
            parents.setFirstName(user.getFirstName());
            parents.setLastName(user.getLastName());
            parents.setEmail(user.getEmail());
            parents.setUserName(user.getUserName());
            parents.setPassword(user.getPassword());

            parents.ParentsMain(parents, connection);
        } else if (user.getDepartamentId() == 6) {
            Bookkeeper bookkeeper = new Bookkeeper();

            bookkeeper.setId(user.getId());
            bookkeeper.setFirstName(user.getFirstName());
            bookkeeper.setLastName(user.getLastName());
            bookkeeper.setEmail(user.getEmail());
            bookkeeper.setUserName(user.getUserName());
            bookkeeper.setPassword(user.getPassword());

            bookkeeper.bookkeeperMain(bookkeeper, connection);
        }
        else {
            System.out.println("Если вы это читаете, значит в приложении есть ошибка с тем, что указали неправильный департамент");
        }
    }

    public static void initializationUser(int idUser, Connection connection) throws SQLException, ParseException {
        PreparedStatement preparedStatement = null;
        String USER_NEW = "SELECT * FROM users WHERE idUsers = '" +idUser+ "' ";
        preparedStatement = connection.prepareStatement(USER_NEW);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = new User();

        while(resultSet.next()){
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setUserName(resultSet.getString(5));
            user.setPassword(resultSet.getString(6));
            user.setDepartamentId(resultSet.getInt(7));
        }

        madeModelAndEntranceInMenu(user, connection);
    }

}
