package com.will.controller;

import com.will.utils.HashPassword;
import com.will.entity.SignupForm;
import com.will.entity.User;
import com.will.service.UserService;
import com.will.validation.UserInputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    /*
        Signup endpoint
        @Params Signup form in json - email, password
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registration(@RequestBody SignupForm signup){
        boolean valid = true;
        if(!UserInputValidation.userEmail(signup.getEmail())){
            logger.warn("Email is invalid: {}", signup.getEmail());
            valid = false;
        }
        if(!UserInputValidation.userPassword(signup.getPassword())){
            logger.warn("Password must be greater than 6 characters: {}", signup.getPassword());//TODO remove this
            valid = false;
        }

        if(valid){
            ArrayList<String> hashedPass = HashPassword.generatePasswordHash(signup.getPassword());
            userService.insertUser(new User(signup.getEmail(), hashedPass));
        }


    }

    /*
        Login endpoint
        @Params Signup form in json - email, passowrd
        NB signup re-used for login
        need to return a session token
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody SignupForm login){

        //Dont query db if email is not valid
        if(!UserInputValidation.userEmail(login.getEmail())){
            logger.warn("This is not a valid email");
        }else{
            User user = userService.getUserByEmail(login.getEmail());
            if(user!=null&&HashPassword.checkPassword(login.getPassword(),user.getPassword().get(0), user.getPassword().get(1))){
                logger.info("Login was successful {}", login);
            }else{
                logger.info("Login was unsuccessful {}", login);
            }
        }

        //Add meaningful session token
        //response.addCookie(new Cookie("webtoken", "dataval"));

    }


}
