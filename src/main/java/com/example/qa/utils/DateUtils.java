package com.example.qa.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Instant now(){
        ZoneId asiaVietnamese = ZoneId.of("Asia/Ho_Chi_Minh");
        return ZonedDateTime.ofInstant(Instant.now(), asiaVietnamese).toInstant();
    }

    public static Date addMinutes(Date date, int minutesBonus){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
        System.out.println("Current Time: " + sdf.format(today));
        calendar.add(Calendar.MINUTE, minutesBonus);
        return calendar.getTime();
    }
}
