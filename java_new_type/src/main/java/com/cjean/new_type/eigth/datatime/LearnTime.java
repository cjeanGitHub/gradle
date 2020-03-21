package com.cjean.new_type.eigth.datatime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LearnTime {

    public static void main(String[] args) {
        testTime();
    }

    static void testTime(){

        //获取当前的时间(年月日)
        LocalDate currentLocalDate = LocalDate.now();
        System.out.println("currentLocalDate: "+currentLocalDate);
        System.out.println("年："+currentLocalDate.getYear());
        //获取当前的天数 一周的第几天
        DayOfWeek dayOfWeek = currentLocalDate.getDayOfWeek();
        System.out.println("dayOfWeek: "+dayOfWeek.getValue());//周四是： 4，周天（0）开始计算

        //获取当前的时间(年月日+时分秒)
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        System.out.println("currentLocalDateTime: "+currentLocalDateTime);

        //获取当前的时间(时分秒)
        LocalTime currentLocalTime = LocalTime.now();
        System.out.println("currentLocalTime: "+currentLocalTime);

    }
}