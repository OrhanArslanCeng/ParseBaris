package com.baris.parsebaris;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by baris on 7.9.2015.
 */
@ParseClassName("_User")
public class User extends ParseObject {


    public String getusername() {
        return getString("username");
    }

    public void setusername(String username) {
        put("username", username);
    }

    @Override
    public String toString() {
        return getString("username");
    }
}
