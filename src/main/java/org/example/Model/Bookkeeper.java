package org.example.Model;

import java.util.logging.Logger;

public class Bookkeeper extends User{
    Logger logger = Logger.getLogger(Bookkeeper.class.getName());
    public Bookkeeper(){super.departamentId = 6;}
    public void bookkeeperMain(Bookkeeper bookkeeper){

        logger.info("Привет Бухгалтер");
    }

}
