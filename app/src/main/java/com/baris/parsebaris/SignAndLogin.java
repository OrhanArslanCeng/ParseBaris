package com.baris.parsebaris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignAndLogin extends Activity {

    Button login_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_and_login);

        login_button = ((Button) findViewById(R.id.login));

        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignAndLogin.this, LoginActivity.class));
            }
        });

        ((Button) findViewById(R.id.signup)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignAndLogin.this, SignUpActivity.class));
            }
        });
    }
}
