package com.iot.myfridge.data;

import com.iot.myfridge.utils.DataUtil;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Noti {

    //private int Nid;
    private int cat; //  0: surpass the exp date, 1: exp, 2: purchase for food (eating habits), 3: eat for healthier eating habits
    private String notiDate; //2019-12-10
    private String content;
    private ArrayList<String> name;

    // constructor
    public Noti(int cat){
        //this.Nid =
        this.cat = cat;
        setCurrentDate();
        this.content = setContent();
    }
    // constructor for cat = 1,2,3
    public Noti(int cat, ArrayList<String> name){
        //this.Nid =
        this.cat = cat;
        setCurrentDate();
        this.name = name;
        this.content = setContent();
    }

    public String setContent(){
        String content = "";
        if (cat == 0){
            if (name.size() <= 1){
                return "Your " + name.toString() + " in fridge has expired already! Throw it!";
            }
            else{
                return "Your " + name.toString() + " in fridge have expired already! Throw them!";
            }
        }
        else if(cat == 1){
            if(name.size() <=1 ){
                return "Your " + name.toString() + " in fridge will be expired Today! Eat it!";
            }
            else{
                return "Your " + name.toString() + " in fridge will be expired Today! Eat them!";
            }
        }
        else if(cat == 2){
            if(name.size() <= 1){
                return "Your " + name.toString() + " is run out! Do not forget to buy it!";
            }
            else {
                return "Your " + name.toString() + " are run out! Do not forget to buy them!";
            }
        }
        else if(cat ==3){
            int d = 2;
            return "You haven't been ate "+ name.toString() + " for "+ d + " days! ";
        }
        else {
            return "none";
        }
    }

    // getter
    public String getContent(){
        return this.content;
    }
    public String getNotiDate(){
        return this.notiDate;
    }
    public void setNotiDate(String dateDay){
        if (Integer.parseInt(dateDay)<10){
            this.notiDate = "2019-12-0"+dateDay;
        }
        else{
            this.notiDate = "2019-12-"+dateDay;
        }
    }
    public void setCurrentDate(){
        Date testDate = new DataUtil().getTodaysDate();
        String testResult = DateFormat.getDateInstance(DateFormat.MEDIUM).format(testDate);
        this.notiDate = testResult;
    }

    //for test
/***
    public static void main (String[] args) {

        ArrayList<Noti> testNotis = new ArrayList<>();

        ArrayList<String> testNames1 = new ArrayList<>();
        testNames1.add("Chicken Wing");
        Noti testNoti1 = new Noti(1,testNames1);
        testNoti1.setNotiDate("6");
        testNotis.add(testNoti1);

        ArrayList<String> testNames2 = new ArrayList<>();
        testNames2.add("Beef");
        Noti testNoti2 = new Noti(1,testNames2);
        testNoti2.setNotiDate("7");
        testNotis.add(testNoti2);

        ArrayList<String> testNames3 = new ArrayList<>();
        testNames3.add("Orange");
        testNames3.add("Apple");
        Noti testNoti3 = new Noti(1,testNames3);
        testNoti3.setNotiDate("8");
        testNotis.add(testNoti3);

        ArrayList<String> testNames4 = new ArrayList<>();
        testNames4.add("Apple");
        Noti testNoti4 = new Noti(0,testNames4);
        testNoti4.setNotiDate("9");
        testNotis.add(testNoti4);

        ArrayList<String> testNames5 = new ArrayList<>();
        testNames5.add("Tomato");
        Noti testNoti5 = new Noti(1,testNames5);
        testNoti5.setNotiDate("10");
        testNotis.add(testNoti5);

        ArrayList<String> testNames6 = new ArrayList<>();
        testNames6.add("Vegetables");
        Noti testNoti6 = new Noti(3,testNames6);
        testNoti6.setNotiDate("10");
        testNotis.add(testNoti6);

        ArrayList<String> testNames7 = new ArrayList<>();
        testNames7.add("Beef");
        Noti testNoti7 = new Noti(1,testNames7);
        testNoti7.setNotiDate("11");
        testNotis.add(testNoti7);

        ArrayList<String> testNames8 = new ArrayList<>();
        testNames8.add("Milk");
        testNames8.add("Apple");
        Noti testNoti8 = new Noti(2,testNames8);
        testNoti8.setNotiDate("12");
        testNotis.add(testNoti8);

        for (Noti j : testNotis){
            System.out.println(j.getContent());
        }
        System.out.println(testNotis.size());

    }
***/
}
