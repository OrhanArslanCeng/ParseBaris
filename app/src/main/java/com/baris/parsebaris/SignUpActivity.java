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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {

    ProgressDialog dlg;
    private EditText ET_username, ET_password, ET_passwordAgain,ET_email;
    public CheckBox CB_showpassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        ET_username = (EditText) findViewById(R.id.et_username);
        ET_password = (EditText) findViewById(R.id.et_password);
        ET_passwordAgain = (EditText) findViewById(R.id.et_passwordAgain);
        ET_email = (EditText) findViewById(R.id.et_email);

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
                StringBuilder hataKontrolMesaj = new StringBuilder(getResources().getString(R.string.error_intro));

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

                if (isEmpty(ET_email)) {
                    if (hataKontrol) {
                        hataKontrolMesaj.append(getResources().getString(R.string.error_join));
                    }
                    hataKontrol = true;
                    hataKontrolMesaj.append(getResources().getString(R.string.error_blank_email));
                }
                if (!isMatching(ET_password, ET_passwordAgain)) {
                    if (hataKontrol) {
                        hataKontrolMesaj.append(getResources().getString(R.string.error_join));
                    }
                    hataKontrol = true;
                    hataKontrolMesaj.append(getResources().getString(R.string.error_mismatched_passwords));
                }
                hataKontrolMesaj.append(getResources().getString(R.string.error_end));


                if (hataKontrol) {
                    Toast.makeText(SignUpActivity.this, hataKontrolMesaj.toString(), Toast.LENGTH_LONG).show();
                    return;
                }


                dlg = new ProgressDialog(SignUpActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Signing up.  Please wait.");
                dlg.show();


                ParseUser user = new ParseUser();
                user.setUsername(ET_username.getText().toString());
                user.setPassword(ET_password.getText().toString());
                user.setEmail(ET_email.getText().toString());


                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(SignUpActivity.this, ChooseIntent.class);
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

    private boolean isMatching(EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}
