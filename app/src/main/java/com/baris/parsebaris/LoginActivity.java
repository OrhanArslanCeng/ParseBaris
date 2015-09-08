package com.baris.parsebaris;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends Activity {


    private EditText ET_username, ET_password;
    public CheckBox CB_showpassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        ET_username = (EditText) findViewById(R.id.et_username);
        ET_password = (EditText) findViewById(R.id.et_password);
        CB_showpassword = (CheckBox) findViewById(R.id.cb_showpassword);

        CB_showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ET_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    ET_password.setInputType(129);
                }
            }
        });


        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                boolean hataKontrol = false;
                StringBuilder hataKontrolMesaj =
                        new StringBuilder(getResources().getString(R.string.error_intro));
                if (isEmpty(ET_username)) {
                    hataKontrol = true;
                    hataKontrolMesaj.append(getResources().getString(R.string.error_blank_username));
                }
                if (isEmpty(ET_password)) {
                    if (hataKontrol) {
                        hataKontrolMesaj.append(getResources().getString(R.string.error_join));
                    }
                    hataKontrol = true;
                    hataKontrolMesaj.append(getResources().getString(R.string.error_blank_password));
                }
                hataKontrolMesaj.append(getResources().getString(R.string.error_end));

                if (hataKontrol) {
                    Toast.makeText(LoginActivity.this, hataKontrolMesaj.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Logging in.  Please wait.");
                dlg.show();


                ParseUser.logInInBackground(ET_username.getText().toString(), ET_password.getText()
                        .toString(), new LogInCallback() {

                    @Override
                    public void done(ParseUser user, ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, ChooseIntent.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });


            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

}
