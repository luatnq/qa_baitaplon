package com.example.qa.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtils {
    public static Instant now(){
        ZoneId asiaVietnamese = ZoneId.of("Asia/Ho_Chi_Minh");
        return ZonedDateTime.ofInstant(Instant.now(), asiaVietnamese).toInstant();
    }
}
