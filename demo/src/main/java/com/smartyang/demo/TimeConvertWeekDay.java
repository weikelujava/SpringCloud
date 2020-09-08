package com.smartyang.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeConvertWeekDay {

    /**
     * 根据星期几获取星期几对应的的日期时间
     * 1，代表周日；2，代表周一... 7代表周六
     * @param week 周几
     * @return
     */
    public static String getDateByWeek(String week){

        int targetWeek = Integer.parseInt(week);
        // 系统时间中0-7代表周日周一周二..周六，对日历中1-7代表的周日周一..周六，时间相差一天
        targetWeek++;
        // 时间格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //当前系统时间
        Date currentDay = new Date();
        String currentDayTime = sdf.format(currentDay.getTime());
        Calendar c = Calendar.getInstance();
        Integer currWeek = getWeekByCurrentDatetime(currentDayTime);
        if(currWeek == Integer.parseInt(week)){
            System.out.println(week + " : " + sdf.format( c.getTime()) );
            return currentDayTime;
        }
        do{
            // 向后推一天，直到星期数与所给的星期数相同
            c.add(Calendar.DAY_OF_MONTH,1);
        }while (targetWeek != c.get(Calendar.DAY_OF_WEEK));

        System.out.println(week + " : " + sdf.format( c.getTime()) );
        return sdf.format( c.getTime());
    }

    public static void main(String[] args) {
//        System.out.println(getWeekByCurrentDatetime("2020-09-09"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-10"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-11"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-12"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-13"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-14"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-15"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-16"));
//        System.out.println(getWeekByCurrentDatetime("2020-09-17"));
        for (int i = 0; i < 7; i++) {
            getDateByWeek(String.valueOf(i));
        }

    }

    /**
     * 判断当天是周几
     * 0代表周日
     * 1-6代表周一到周六
     * @param currentDayTime 当前时间
     * @return 周几
     */
    public static Integer getWeekByCurrentDatetime(String currentDayTime) {
        // 当天时间
//        Date currentDay = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String currentDayTime = sdf.format(currentDay.getTime());
        // 定义一个周数组
        Integer[] weekDays = { 0, 1, 2, 3, 4, 5, 6 };
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = sdf.parse(currentDayTime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 根据当前的系统时间判断下一周，根据周几获取周几对应的时间日期
     * @param number
     * @return
     */
    public static String getWeekDate(String number) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if(dayWeek==1){
            dayWeek = 8;
        }
//        System.out.println(Calendar.DATE);
//        System.out.println(cal.getFirstDayOfWeek());
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
                cal.add(Calendar.DATE, cal.getFirstDayOfWeek() + (Integer.parseInt(number)-4));// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
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
