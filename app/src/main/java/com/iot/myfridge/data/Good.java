package com.iot.myfridge.data;

public class Good {
    private int Bid;
    private String name;
    private String label;
    private int calorie;

    // constructor
    public Good(){};

    // getter
    public int getBid() {
        return Bid;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public int getCalorie() {
        return calorie;
    }
}
