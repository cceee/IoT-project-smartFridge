package com.iot.myfridge.data;

import com.iot.myfridge.utils.Constants;
import com.iot.myfridge.utils.DataUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class BalanceGood {

    private String Bid;
    private String name;
    private String storeDate;   // int year, int month, int date
    private Date expDate;
    private int leftDay;
    private String label;    // same as original basicGood.label
    private int calories; // total calories
    private int quantity;

    // constructor
    public BalanceGood(){
        //this.leftDay = ;
    };

    public BalanceGood(String name, String storeDate, int quantity){

        this.Bid = UUID.randomUUID().toString();
        this.name = name;
        this.storeDate = "2019-12-"+storeDate;
        //this.expDate = expDate;
        //this.leftDay = new DataUtil().getDateDiff(storeDate,expDate);
        this.label = new Constants().NameLabel().get(name);
        this.calories = new Constants().NameCal().get(name);
        this.quantity = quantity;
    }
    public BalanceGood(String name, int quantity){

        this.Bid = UUID.randomUUID().toString();
        this.name = name;
        this.storeDate = "2019-12-12";
        //this.expDate = expDate;
        //this.leftDay = new DataUtil().getDateDiff(storeDate,expDate);
        this.label = new Constants().NameLabel().get(name);
        this.calories = new Constants().NameCal().get(name);
        this.quantity = quantity;
    }

    // setter
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
        //this.leftDay = ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
    }

    public void setLeftDay(int leftDay) {
        this.leftDay = leftDay;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // getter

    public String getBid() {
        return Bid;
    }

    public String getName() {
        return name;
    }

    public String getStoreDate() {
        return storeDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public int getLeftDay() {
        return leftDay;
    }

    public String getLabel() {
        return label;
    }

    public int getCalories() {
        return calories;
    }

    public int getQuantity() {
        return quantity;
    }

}

