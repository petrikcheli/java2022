package org.example.klient;

import org.example.Main;
import org.example.Model.User;
import java.util.logging.Logger;
import java.sql.Connection;


public class Parents extends User{
    public Parents(){super.departamentId = 5;}

    public void ParentsMain(Parents parents, Connection connection){
        Logger logger = Logger.getLogger(Parents.class.getName());

    }
}
