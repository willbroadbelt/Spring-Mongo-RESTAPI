package com.will.controller;

import com.will.forms.LoginForm;
import com.will.utils.HashPassword;
import com.will.forms.SignupForm;
import com.will.entity.User;
import com.will.service.UserService;
import com.will.validation.UserInputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity<String> registration(@RequestBody SignupForm signup){
        if(!UserInputValidation.userEmail(signup.getEmail())){
            logger.warn("Email is invalid: {}", signup.getEmail());
            return new ResponseEntity<>("Email is invalid", HttpStatus.BAD_REQUEST);
        }
        if(!UserInputValidation.userPassword(signup.getPassword())){
            logger.warn("Password is invalid: {}", signup.getPassword());//TODO remove this
            return new ResponseEntity<>("Password must be greater than 6 characters", HttpStatus.BAD_REQUEST);
        }

        ArrayList<String> hashedPass = HashPassword.generatePasswordHash(signup.getPassword());
        userService.insertUser(new User(signup.getEmail(), hashedPass, signup.getLocations()));
        return new ResponseEntity<>("Registration was successful", HttpStatus.OK);


    }

    /*
        Login endpoint
        @Params Signup form in json - email, passowrd
        NB signup re-used for login
        need to return a session token
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginForm login, HttpSession session){

        //Dont query db if email is not valid
        if(!UserInputValidation.userEmail(login.getEmail())){
            logger.warn("This is not a valid email");
            return new ResponseEntity<>("Email is invalid", HttpStatus.BAD_REQUEST);
        }else{
            User user = userService.getUserByEmail(login.getEmail());
            if(user!=null&&HashPassword.checkPassword(login.getPassword(),user.getPassword().get(0), user.getPassword().get(1))){
                logger.info("Login was successful {}", login);
                session.setAttribute("email", login.getEmail());
                return new ResponseEntity<>("Login was successful", HttpStatus.OK);
            }else{
                logger.info("Login was unsuccessful {}", login);
                return new ResponseEntity<>("Password incorrect", HttpStatus.UNAUTHORIZED);
            }
        }

    }


}
