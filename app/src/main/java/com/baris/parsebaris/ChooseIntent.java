package com.baris.parsebaris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;


public class ChooseIntent extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ParseUser.getCurrentUser() != null) {
            startActivity(new Intent(this, MyActivity.class));
        } else {
            startActivity(new Intent(this, SignAndLogin.class));
        }
    }
}
