package com.iot.myfridge.data;

import com.iot.myfridge.utils.Constants;
import com.iot.myfridge.utils.DataUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class HistoryGood {

    private String Hid;
    private String name;
    private String eatenDate;
    private int calories;
    private int quantity;
    private String label;
    private double protein;
    private double fat;
    private double carbs;
    private double weight;
    private int icon;

    // constructor
    public HistoryGood(){};
    public HistoryGood(String name, String eatenDate, int quantity){
        this.Hid = UUID.randomUUID().toString();
        this.name = name;
        this.quantity =quantity;
        this.eatenDate = "2019-12-"+eatenDate;
        this.calories = (Integer)new Constants().NameCal().get(name)*this.quantity;
        this.protein = new Constants().NamePros().get(name)* this.quantity;
        this.fat = new Constants().NameFat().get(name) * this.quantity;
        this.carbs = new Constants().NameCarb().get(name) * this.quantity;
        this.weight = new Constants().NameCarb().get(name) * this.quantity;
        this.label = new Constants().NameLabel().get(name);
    }
    public HistoryGood(String Hid){
        this.Hid = Hid;
    }
    // setter

    public void setName(String name) {
        this.name = name;
    }

    public void setEatenDate(String eatenDate) {
        this.eatenDate = eatenDate;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    // getter

    public String getHid() {
        return Hid;
    }

    public String getName() {
        return name;
    }

    public String getEatenDate() {
        return eatenDate;
    }

    public int getCalories() {
        return calories;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLabel() {
        return label;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getWeight() {
        return weight;
    }
}
