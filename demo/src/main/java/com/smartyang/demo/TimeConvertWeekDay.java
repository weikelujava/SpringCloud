package com.smartyang.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeConvertWeekDay {


    public static void main(String[] args) {
//        System.out.println(dateToWeek("2020-09-08"));
//        System.out.println(getWeekDate("2020-09-08","2"));
//        System.out.println(getFetureDate(7));
        for (int i = 0; i < 60; i++) {
            getWeekDate(String.valueOf(i));
        }

    }

    /**
     * 根据当前的系统时间判断下一周，根据周几获取周几对应的时间日期
     * @param number
     * @return
     */
    public static String getWeekDate(String number) {
        Map<String,String> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
        String weekDate = "";
        switch (number){
            case "0":
                cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
                Date sundayDate = cal.getTime();
                weekDate = sdf.format(sundayDate);
                System.out.println("周7："+weekDate);
                break;
            case "1":
                cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
                Date mondayDate = cal.getTime();
                weekDate = sdf.format(mondayDate);
                System.out.println("周1："+weekDate);
                break;
            default:
                cal.add(Calendar.DATE, cal.getFirstDayOfWeek() + (Integer.parseInt(number)-3));// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
                Date mondayDate1 = cal.getTime();
                weekDate = sdf.format(mondayDate1);
                String num = getWeekDate1(weekDate);
                if("0".equals(num)){
                    System.out.println("周7"+"："+weekDate);
                }
                System.out.println("周"+(getWeekDate1(weekDate))+"："+weekDate);
                break;
        }
        return weekDate;
    }



    public static String getWeekDate1(String datetime) {
        // 当天
        Date currentDay = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDayTime = sdf.format(currentDay.getTime());
//        System.out.println(currentDayTime);

        String[] weekDays = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Map<String,String> weekDateMap = new LinkedHashMap<>();
        Date datet = null;
        try {
            datet = sdf.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
            return weekDays[w];
    }

    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        System.out.println(result);
        return result;
    }

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
