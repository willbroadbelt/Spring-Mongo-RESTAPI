package com.will.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OWMHandlerTest {

    @Test
    public void getWeatherTest(){
        String weather = OWMHandler.getWeather("London,UK");
        assertTrue(weather.matches("City: London.*"));
    }

    //TODO: Test getting the weather from a fake location
}
