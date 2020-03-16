package com.iot.myfridge.utils;

import android.util.Log;

import com.iot.myfridge.database.DatabaseHandler;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataUtil {

    DatabaseHandler dbHandler;
    public DataUtil (DatabaseHandler dbHandler){
        this.dbHandler = dbHandler;
    }
    public DataUtil (){}

    // get current date for store date and eaten date
    public Date getTodaysDate(){
        Calendar today = Calendar.getInstance();
        today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
        Date todayDate = today.getTime();
        return todayDate;
    }
    // to calculate left days
    public int getDateDiff(Date date1, Date date2) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return (int) TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    // add day amount to a particular date
    public Date addDaysToDate(Date date, int days)
    {
        if(date != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days);
            return cal.getTime();
        }
        else return null;
    }

    public Date parseDates(String datestr){
        SimpleDateFormat sdf;
        Date date = null;

        String[] dateFormates = new String[5];
        dateFormates[0] = "EEE MMM dd HH:mm:ss z yyyy";
        dateFormates[1] = "yyyy-MM-dd";
        dateFormates[2] = "MMM dd, yyyy";
        dateFormates[3] = "EEE MMM dd HH:mm:ss";
        dateFormates[4] = "yyyy-MM-dd";

        for(int i=0;i<dateFormates.length;i++){
            try {
                sdf = new SimpleDateFormat(dateFormates[i], Locale.US);
                date=sdf.parse(datestr);
                Log.d("prefDate","first:"+ datestr);
                return date;
            } catch (ParseException e) {
                try {
                    sdf = new SimpleDateFormat(dateFormates[i], Locale.GERMAN);
                    date=sdf.parse(datestr);
                    Log.d("prefDate","first:"+ datestr);
                    return date;
                } catch (ParseException y) {
                    y.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return null;
    }

    public String strToUtf8(String str){
        byte[] byteArray = new byte[0];
        String utfText = "";
        try {
            byteArray = str.getBytes("UTF-8");
            utfText = new String( byteArray, "UTF-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utfText;
    }
}
