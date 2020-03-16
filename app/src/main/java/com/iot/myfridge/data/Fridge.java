package com.iot.myfridge.data;

public class Fridge {

    private String path;
    private double temperature;

    public Fridge(String path){
        this.path = path;
    };

    public String getPath() {
        return path;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
