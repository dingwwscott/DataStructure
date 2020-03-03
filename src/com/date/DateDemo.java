package com.date;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateDemo {
    public static void main(String[] args) {
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }
}
