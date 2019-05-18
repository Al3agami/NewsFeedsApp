package com.agamidev.newsfeedsapp.Utils;

import android.util.Log;

import com.agamidev.newsfeedsapp.Models.NewsModel;

public class NewsDate {

    public static String getFinalDate(NewsModel n){
        String date = splitString(n.getPublishedAt(),"T")[0];
        String[] dateArray = splitString(date,"-");
        String year = dateArray[0];
        String month = dateArray[1];
        String day = dateArray[2];
        return getMonthName(month)+" "+day+", "+year;
    }


    private static String[] splitString(String s, String splitChar){
        String[] myArray = s.split(splitChar);
        return myArray;
    }

    private static String getMonthName(String month){
        String monthName;

        switch(month)
        {
            case "01":
                monthName = "January";
                break;
            case "02":
                monthName = "February";
                break;
            case "03":
                monthName = "March";
                break;
            case "04":
                monthName = "April";
                break;
            case "05":
                monthName = "May";
                break;
            case "06":
                monthName = "June";
                break;
            case "07":
                monthName = "July";
                break;
            case "08":
                monthName = "August";
                break;
            case "09":
                monthName = "September";
                break;
            case "10":
                monthName = "October";
                break;
            case "11":
                monthName = "November";
                break;
            case "12":
                monthName = "December";
                break;
            default:
                monthName = " ";
        }

        return monthName;
    }
}
