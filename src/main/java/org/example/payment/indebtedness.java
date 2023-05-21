package org.example.payment;


import lombok.Getter;
import lombok.Setter;

import org.example.klient.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;

@Getter
@Setter
public class indebtedness {
    protected Integer idParents;
    protected Integer money;
    protected Integer ActiveMode; // 1 - задолженость актуальна; 2 - задолженость не актуальна


    private static String Time(int countDay) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, countDay);
        String day = ((String.valueOf(calendar.getTime())).split(" ")[3]).split("-")[0];
        String month = ((String.valueOf(calendar.getTime())).split(" ")[3]).split("-")[1];        //String.valueOf((1 + Integer.parseInt(String.valueOf(calendar.get(Calendar.MONTH)))));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String time = (String.valueOf(calendar.getTime())).split(" ")[3];
        String date = year+"-"+"0"+month+"-"+day+" "+time;
        return date;
    }

    public void SetDept(int idparents, Connection connection) throws ParseException{
        String thirdDay = Time(3);
        String tenthDay = Time(10);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormatter .parse(thirdDay);

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it once
        timer.schedule(, date); //что нужно запустить и когда
        timer.schedule(, tenthDay);


    }
    public void RemoveDept(int idparents, Connection connection){

    }
}
