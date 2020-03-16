package com.iot.myfridge.utils;

import com.iot.myfridge.R;
import com.iot.myfridge.data.Msg;
import com.iot.myfridge.data.Noti;

import java.util.ArrayList;
import java.util.List;

public class MsgUtil {

    public static List<Msg> getMsgList(){

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


        List<Msg> msgList = new ArrayList<>();

        Msg msg = new Msg(1, R.drawable.apple,

                testNotis.get(7).getNotiDate(),

                testNotis.get(7).getContent());

        msgList.add(msg);

        msg = new Msg(2,R.drawable.beef,
                testNotis.get(6).getNotiDate(),

                testNotis.get(6).getContent());


        msgList.add(msg);

        msg = new Msg(3,R.drawable.tomato,
                testNotis.get(4).getNotiDate(),

                testNotis.get(4).getContent()+"\n"+testNotis.get(5).getContent());


        msgList.add(msg);

        msg = new Msg(4,R.drawable.apple,

                testNotis.get(3).getNotiDate(),

                testNotis.get(3).getContent());
        msgList.add(msg);

        msg = new Msg(5,R.drawable.orange,
                testNotis.get(2).getNotiDate(),

                testNotis.get(2).getContent());

        msgList.add(msg);
        msg = new Msg(6,R.drawable.beef,

                testNotis.get(1).getNotiDate(),

                testNotis.get(1).getContent());

        msgList.add(msg);
        msg = new Msg(7,R.drawable.chicken_wing,
                testNotis.get(0).getNotiDate(),

                testNotis.get(0).getContent());
        msgList.add(msg);



        return msgList;

    }

}
