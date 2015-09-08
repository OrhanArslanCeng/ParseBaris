package com.baris.parsebaris;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by baris on 7.9.2015.
 */
@ParseClassName("Pets")
public class Pets extends ParseObject {

    public String getName(){
        return getString("Name");
    }

    public void setName(String name){
        put("Name", name);
    }

    public String getType(){
        return getString("Type");
    }

    public void setType(String type){
        put("Type", type);
    }

    @Override
    public String toString(){
        return getString("Name") + "\n" + getString("Type");
    }
}