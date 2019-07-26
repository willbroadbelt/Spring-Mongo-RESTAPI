package com.will.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SignupForm {
    private String email;
    private String password;
    private List<String> locations;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getLocations() {
        return locations;
    }
}
