package com.will.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.will.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Class to help opening and managing calls to the OpenWeatherMap API
    https://openweathermap.org/
 */
public class OWMHandler {
    private static final Logger logger = LoggerFactory.getLogger(OWMHandler.class);


    private static String weatherKey = "bac27dba9f458aa25681c9585ac35512";
    private static String owmUrl = "https://api.openweathermap.org/data/2.5/weather?q=";


    public static String getWeather(String location){
        String requestUrlString = owmUrl+location + "&appid=" + weatherKey + "&units=metric";
        StringBuilder rawResult = new StringBuilder();
        try {
            URL url = new URL(requestUrlString);
            URLConnection connection = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = rd.readLine()) != null){
                rawResult.append(line);
            }
            rd.close();

            Map<String, Object> responseMap = jsonToMap(rawResult.toString());//new ObjectMapper().readValue(rawResult.toString(), HashMap.class);

            return formWeatherResponse(responseMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    //Creates a nice plain text version of the current weather
    public static String formWeatherResponse(Map<String, Object> fullMap){
        Map<String, Object> mainMap = jsonToMap(fullMap.get("main").toString());
        //Weather is an ArrayList with one entry
        ArrayList<Object> weatherArray = (ArrayList<Object>)(fullMap.get("weather"));
        //Had to remove all white space as the description space ruins the json parsing
        Map<String, Object> weatherMap = jsonToMap(weatherArray.get(0).toString().replaceAll("\\s+",""));

        String name = fullMap.get("name").toString();
        String desc = weatherMap.get("description").toString();
        String temp = mainMap.get("temp").toString();
        String report = "City: "+ name + " Current weather: " + desc + " Temperature: "+temp;
        logger.info(report);

        return report;

    }

    //Converts a String holding a json into a map of <String, Object> pairs to help facilitate extracting values
    private static Map<String, Object> jsonToMap(String str){
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
        Map<String, Object> map = new Gson().fromJson(str, type);
        return map;
    }

    public static String[] getAllLocationsWeather(List<String> locations){
        int size = locations.size();
        String[] weathers = new String[size];
        for(int i = 0; i< size; i++){
            weathers[i] = getWeather(locations.get(i));
        }
        return weathers;
    }

}
