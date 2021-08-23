package com.tennisscorer.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonHelper {

    public static Date castStringToDate(String date) throws ParseException {
        String yy = date.substring(0,4);
        String mm = date.substring(5,6);
        String dd = date.substring(7,8);
        String newDate = dd + "/" + mm + "/"  + yy;
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(newDate);
        return date1;
    }

    public static Boolean compareTwoDate(String dateStart, String dateEnd, String date)
    {
        if(Integer.parseInt(dateStart) <= Integer.parseInt(date) && Integer.parseInt(date) <= Integer.parseInt(dateEnd) ){
            return true;
        }else{
            return false;
        }

    }


}
