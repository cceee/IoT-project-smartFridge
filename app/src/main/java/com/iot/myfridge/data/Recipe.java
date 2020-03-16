package com.iot.myfridge.data;

import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

public class Recipe{

    private ArrayList<String> ingredients;
    private static String app_key = "1b3ff5d8dcb033e89ce5f2e843620a3f";
    private static String app_id  = "5dc22333";
    private static String path = "https://api.edamam.com/search?";
    private boolean succ;
    private String resp;

    public Recipe(ArrayList<String> ingredients) throws Exception{
        this.ingredients.addAll(ingredients);
        sendGet();
        //succ=searchRecipe();
    }
    private String sendGet() {
        resp = "";
        String url = path + "q=" + ingredients.toString() + "&app_id="+app_id+"app_key="+app_key+"&from=0&to=3";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                resp = new String(bytes);
                resp = "Get response from huzzah: " + resp;
                System.out.println(resp);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
               // Toast.makeText(getApplicationContext(), "Cannot not reach server: ", Toast.LENGTH_LONG).show();
                resp="fail";
            }
        });
        return resp;
    }

    private boolean searchRecipe() throws Exception{

        try {
            String url = path + "q=" + ingredients.toString() + "&app_id="+app_id+"&app_key="+app_key+"&from=0&to=3";
            //https://api.edamam.com/search?q=chicken&app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}&from=0&to=3&calories=591-722&health=alcohol-free;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print in String
            System.out.println(response.toString());

            //Read JSON response and print
            JSONObject myResponse = new JSONObject(response.toString());
            System.out.println("result after Reading JSON Response");
            System.out.println("origin- "+myResponse.getString("origin"));


        }catch (Exception e){
            Logger.getGlobal().warning(e.getMessage());
        }

        return true;

    }

    public String getResp() {
        return resp;
    }

    public boolean getSucc(){
        return succ;
    }
    /***
    public static void main(String[] args)throws Exception {
        try{
            ArrayList<String> ingredients = new ArrayList<>();
            ingredients.add("tomatoes");
            ingredients.add("egg");
            System.out.println(ingredients.toString());
            Recipe r = new Recipe(ingredients);
            System.out.println(r.getResp());
        }catch (Exception e){
            Logger.getGlobal().warning(e.getMessage());
        }

    }
     ***/
}
