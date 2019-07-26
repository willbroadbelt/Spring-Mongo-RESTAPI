package com.will.controller;

import com.will.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ResponseEntity<String> weather(HttpSession session){
        String sessionEmail = session.getAttribute("email").toString();
        if(sessionEmail!=null){
            logger.info("Weather session email {}", sessionEmail);
            List<String> locations = userService.getUserByEmail(sessionEmail).getLocations();
            return new ResponseEntity<>("The locations associated with you are: "+locations, HttpStatus.OK);
        }else{
            logger.info("Accessed weather without a session email");
            return new ResponseEntity<>("No locations available", HttpStatus.NO_CONTENT);//TODO check http code
        }

    }
}
