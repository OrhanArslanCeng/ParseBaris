package com.baris.parsebaris;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends ListActivity {
    List<Pets> pets = new ArrayList<Pets>();
  //  List<User> users = new ArrayList<User>();
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ParseQuery<User> query2 = new ParseQuery<User>("User");


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

                ArrayAdapter<Pets> adapter = new ArrayAdapter<Pets>(MyActivity.this, android.R.layout.simple_list_item_1, pets);
                setListAdapter(adapter);
            }
        });


        logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOut();
                startActivity(new Intent(MyActivity.this, ChooseIntent.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            ParseUser.getCurrentUser().logOut();
            startActivity(new Intent(MyActivity.this, ChooseIntent.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
