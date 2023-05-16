package org.example.Model;


import java.util.logging.Logger;

public class TechSupport extends User{
    Logger logger;
    public void TechSupport(){super.departamentId = 3;}
    public void TechSupportMain(TechSupport techSupport){
        System.out.println("Привет Тех поддержка");
        logger.info("Привет Тех поддержка");
    }
}
