package com.baris.parsebaris;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class InializeParse extends Application {

    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Pets.class);
       // Parse.initialize(this, "ignlCMDQhYVGvCvqKu6V3oHcGis2XLxtgKi0GZUI", "8fg28jbU2zWfUO40Kas10KHPjWg9jI3qBeYAc2uL");
        Parse.initialize(this, "EgrE0wyTnGtxKqSPSNMygYb9SdIr2e0HTjDNGx6t", "l2535CTRluCoJGJQ31gFzTkfn9CgIrFZ6Cruyx5y");

    }
}
