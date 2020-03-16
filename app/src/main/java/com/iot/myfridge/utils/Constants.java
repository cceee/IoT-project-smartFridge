package com.iot.myfridge.utils;

import android.support.annotation.NonNull;

import com.iot.myfridge.data.CurrentGood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Constants {
    public static class VALUES {
        public static final String APP_ID = "df2ce90f";
        public static final String APP_KEY = "077a10621f1c6fc9c58e63267587ea88";
    }

    public static class URLS {
        public static final String BASE_URL = "https://api.edamam.com/";
    }
    public static class FOODLISTS{
        //need to update
        public static final String[] FOOD_LISTS = {"Apple", "Orange", "Pear", "Banana", "Pineapple","Broccoli","Orange Juice", "Juice", "Beef", "Pizza", "Lemon","Carrot", "Corn"};
    }
    /***
    //test
    public static void main(String[] args){
        String test = "Fruit/Food/Plant/Apple/Orange/Monitor/";
        String[] foodRawDetected = test.split("/");
        ArrayList<String> foodDetected = new ArrayList<>();
        for (String i : foodRawDetected){
            if(Arrays.asList(FOODLISTS.FOOD_LISTS).contains(i)){
                foodDetected.add(i);
            }
        }
        System.out.println(foodDetected);
    }
     ***/
    public Map<String, String> LabelUnit(){
        Map<String, String> labelUnits= new HashMap<>();
        labelUnits.put("Vegetables","dish");
        labelUnits.put("Bread","slice");
        labelUnits.put("Rice","bowl");
        labelUnits.put("Staple Food","bowl");
        labelUnits.put("Coarse Grain","portion");
        labelUnits.put("Fruit","each");
        labelUnits.put("Milk","bottle");
        labelUnits.put("Egg","each");
        labelUnits.put("Meat","portion");
        labelUnits.put("Sea Food","portion");
        labelUnits.put("Fish","piece");
        labelUnits.put("Sweets","portion");
        labelUnits.put("Junk Food","portion");
        labelUnits.put("Sugar Drinks","bottle");
        // 14
        return labelUnits;
    }
    public Map<String, Integer> LabelPyramid(){
        Map<String, Integer> labelPyramids= new HashMap<>();
        //0->4 from bottom to top 1->5
        labelPyramids.put("Bread",0);
        labelPyramids.put("Rice",0);
        labelPyramids.put("Coarse Grain",0);
        labelPyramids.put("Vegetables",1);
        labelPyramids.put("Fruit",2);
        labelPyramids.put("Milk",3);
        labelPyramids.put("Egg",3);
        labelPyramids.put("Meat",3);
        labelPyramids.put("Sea Food",3);
        labelPyramids.put("Fish",3);
        labelPyramids.put("Sweets",4);
        labelPyramids.put("Junk Food",4);
        labelPyramids.put("Sugar Drinks",4);
        return labelPyramids;
    }

    public Map<String, String> NameLabel() {
        Map<String, String> nameLabels = new HashMap<>();
        nameLabels.put("Milk","Milk");
        nameLabels.put("Egg","Egg");
        nameLabels.put("Orange","Fruit");
        nameLabels.put("Bread","Bread");
        nameLabels.put("Fish","Fish");
        nameLabels.put("Juice","Sugar Drinks");
        nameLabels.put("Broccoli","Vegetables");
        nameLabels.put("Carrot","Vegetables");
        nameLabels.put("Spinach","Vegetables");
        nameLabels.put("Chicken Wing","Meat");
        nameLabels.put("Chicken Salad","Meat");
        nameLabels.put("Apple","Fruit");
        nameLabels.put("Beef","Meat");
        nameLabels.put("Corn","Vegetables");
        nameLabels.put("Tomato","Vegetables");
        nameLabels.put("Ice Cream","Sweets");
        nameLabels.put("Shrimp","Sea Food");
        nameLabels.put("Cheese Cake","Sweets");
        nameLabels.put("Sushi","Coarse Grain");
        nameLabels.put("Potato","Vegetables");
        nameLabels.put("Ham","Meat");
        nameLabels.put("Lemon","Fruit");
        nameLabels.put("Pizza", "Bread");
        return nameLabels;
    }

    public Map<String, Integer> NameCal(){
        Map<String, Integer> nameCals = new HashMap<>();
        nameCals.put("Milk",130);
        nameCals.put("Egg",70);
        nameCals.put("Orange",120);
        nameCals.put("Bread",157);
        nameCals.put("Fish",315);
        nameCals.put("Juice",110);
        nameCals.put("Broccoli",57);
        nameCals.put("Carrot",92);
        nameCals.put("Spinach",42);
        nameCals.put("Chicken Wing",291);
        nameCals.put("Chicken Salad",130);
        nameCals.put("Apple",111);
        nameCals.put("Beef",498);
        nameCals.put("Corn",224);
        nameCals.put("Tomato",26);
        nameCals.put("Ice Cream",131);
        nameCals.put("Shrimp",76);
        nameCals.put("Cheese Cake",593);
        nameCals.put("Sushi",314);
        nameCals.put("Potato",162);
        nameCals.put("Ham",495);
        nameCals.put("Rice",180);
        nameCals.put("Lemon",100);
        nameCals.put("Pizza",230);
        return nameCals;
    }

    public Map<String, Integer> NameWeight(){
        Map<String,Integer> nameWeights = new HashMap<>();
        // 单位是克， 每单位多少克
        nameWeights.put("Milk",250);
        nameWeights.put("Egg",60);
        nameWeights.put("Orange",250);
        nameWeights.put("Bread",50);
        nameWeights.put("Fish",300);
        nameWeights.put("Juice",250);
        nameWeights.put("Broccoli",150);
        nameWeights.put("Carrot",150);
        nameWeights.put("Spinach",150);
        nameWeights.put("Chicken Wing",150);
        nameWeights.put("Chicken Salad",180);
        nameWeights.put("Apple",210);
        nameWeights.put("Beef",150);
        nameWeights.put("Corn",200);
        nameWeights.put("Tomato",170);
        nameWeights.put("Ice Cream",90);
        nameWeights.put("Shrimp",150);
        nameWeights.put("Cheese Cake",170);
        nameWeights.put("Sushi",200);
        nameWeights.put("Potato",200);
        nameWeights.put("Ham",150);
        nameWeights.put("Rice",150);
        nameWeights.put("Lemon",90);
        nameWeights.put("Pizza",180);
        return nameWeights;
    }

    public Map<String, Double> NameCarb(){
        Map<String, Double> nameCarbs = new HashMap<>();
        // 单位是克， 每单位多少克碳水化合物
        nameCarbs.put("Milk",8.5);
        nameCarbs.put("Egg",1.68);
        nameCarbs.put("Orange",27.75);
        nameCarbs.put("Bread",29.3);
        nameCarbs.put("Fish",3.6);
        nameCarbs.put("Juice",27.0);
        nameCarbs.put("Broccoli",2.7);
        nameCarbs.put("Carrot",9.9);
        nameCarbs.put("Spinach",6.75);
        nameCarbs.put("Chicken Wing",6.9);
        nameCarbs.put("Chicken Salad",4.2);
        nameCarbs.put("Apple",28.77);
        nameCarbs.put("Beef",0.0);
        nameCarbs.put("Corn",45.6);
        nameCarbs.put("Tomato",5.61);
        nameCarbs.put("Ice Cream",22.32);
        nameCarbs.put("Shrimp",2.4);
        nameCarbs.put("Cheese Cake",35.36);
        nameCarbs.put("Sushi",54.8);
        nameCarbs.put("Potato",35.6);
        nameCarbs.put("Ham",7.35);
        nameCarbs.put("Rice", 30.0);
        nameCarbs.put("Lemon",23.0);
        nameCarbs.put("Pizza",39.0);
        return nameCarbs;
    }

    public Map<String, Double> NameFat(){
        Map<String,Double> nameFat = new HashMap<>();
        // 单位是克， 每单位多少克脂肪
        nameFat.put("Milk",8.0);
        nameFat.put("Egg",5.28);
        nameFat.put("Orange",0.5);
        nameFat.put("Bread",2.55);
        nameFat.put("Fish",11.1);
        nameFat.put("Juice",0.0);
        nameFat.put("Broccoli",1.35);
        nameFat.put("Carrot",6.0);
        nameFat.put("Spinach",0.45);
        nameFat.put("Chicken Wing",17.7);
        nameFat.put("Chicken Salad",5.6);
        nameFat.put("Apple",0.3);
        nameFat.put("Beef",43.95);
        nameFat.put("Corn",2.4);
        nameFat.put("Tomato",0.34);
        nameFat.put("Ice Cream",3.51);
        nameFat.put("Shrimp",0.75);
        nameFat.put("Cheese Cake",37.23);
        nameFat.put("Sushi",6.4);
        nameFat.put("Potato",0.4);
        nameFat.put("Ham",41.1);
        nameFat.put("Rice", 0.3);
        nameFat.put("Lemon",0.2);
        nameFat.put("Pizza",23.0);
        return nameFat;
    }
    public Map<String, Double> NamePros(){
        Map<String, Double> namePros = new HashMap<>();
        // 单位是克， 每单位多少克蛋白质
        namePros.put("Milk",7.5);
        namePros.put("Egg",7.98);
        namePros.put("Orange",2.0);
        namePros.put("Bread",4.15);
        namePros.put("Fish",50.4);
        namePros.put("Juice",2.0);
        namePros.put("Broccoli",6.6);
        namePros.put("Carrot",1.35);
        namePros.put("Spinach",3.9);
        namePros.put("Chicken Wing",26.1);
        namePros.put("Chicken Salad",15.2);
        namePros.put("Apple",0.6);
        namePros.put("Beef",25.65);
        namePros.put("Corn",8.0);
        namePros.put("Tomato",1.53);
        namePros.put("Ice Cream",2.7);
        namePros.put("Shrimp",27.45);
        namePros.put("Cheese Cake",29.92);
        namePros.put("Sushi",8.8);
        namePros.put("Potato",5.2);
        namePros.put("Ham",24.0);
        namePros.put("Rice", 0.2);
        namePros.put("Lemon", 0.1);
        namePros.put("Pizza",2.0);
        return namePros;
    }


}
