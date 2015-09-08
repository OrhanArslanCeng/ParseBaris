package com.baris.parsebaris;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baris on 7.9.2015.
 */
public class ParseListActivity extends ListActivity {

    List<Pets> pets = new ArrayList<Pets>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parselist);

        ParseQuery<Pets> query = new ParseQuery<Pets>("Pets");
        query.findInBackground(new FindCallback<Pets>() {
            @Override
            public void done(List<Pets> list, ParseException e) {

                for (Pets pet : list) {
                    Pets newPet = new Pets();
                    newPet.setName(pet.getName());
                    newPet.setType(pet.getType());
                    pets.add(newPet);
//                    Log.i(newPet.getName(), "dalkasdl");
//                    Log.i(newPet.getName().toString(), "dalkasdl");

                }

                ArrayAdapter<Pets> adapter = new ArrayAdapter<Pets>(ParseListActivity.this, android.R.layout.simple_list_item_1, pets);
                setListAdapter(adapter);
            }
        });


    }
}


//        List<User> users = new ArrayList<User>();
//
//        @Override
//        protected void onCreate (Bundle savedInstanceState){
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.parselist);
//
//            ParseQuery<User> query = new ParseQuery<User>("_User");
//            query.findInBackground(new FindCallback<User>() {
//                @Override
//                public void done(List<User> objects, ParseException e) {
//                    for (User user : objects) {
//                        User newUser = new User();
//                        newUser.setusername(user.getusername());
//                        users.add(newUser);
//
//                        ArrayAdapter<User> adapter = new ArrayAdapter<User>(ParseListActivity.this, android.R.layout.simple_list_item_1, users);
//                        setListAdapter(adapter);
//
//                        //Log.i(user.getUsername(), "afladsl");
//
//                    }
//                }
//            });
//        }
//    }

