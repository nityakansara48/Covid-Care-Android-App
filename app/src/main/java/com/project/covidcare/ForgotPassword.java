package com.project.covidcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    EditText pinCode;
    EditText password;
    EditText password1;
    Button forgotLogin;
    Button forgotResetPassword;
    DBConnection DB;
    String newEmail = "";
    String newPinCode = "";
    String pass = "";
    String pass1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.forgotEmail1);
        pinCode = (EditText) findViewById(R.id.forgotPinCode1);
        password = (EditText) findViewById(R.id.newPassword1);
        password1 = (EditText) findViewById(R.id.newPassword11);
        forgotLogin = (Button) findViewById(R.id.forgotLogin);
        forgotResetPassword = (Button) findViewById(R.id.resetPassword);

        DB = new DBConnection(this);

        forgotResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newEmail = email.getText().toString();
                newPinCode = pinCode.getText().toString();
                pass = password.getText().toString();
                pass1 = password1.getText().toString();

                if (newEmail.equals("") || newPinCode.equals("") || pass.equals("") || pass1.equals("")) {
                    Toast.makeText(ForgotPassword.this, "Enter All Details.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(pass1)) {
                        String resetPasswordResult = DB.resetPassword(newEmail, newPinCode, pass);
                        if (resetPasswordResult.equals("Invalid Email ID")) {
                            Toast.makeText(ForgotPassword.this, "Invalid Email ID.", Toast.LENGTH_SHORT).show();
                            System.out.println("11111111111111111");
                        } else if (resetPasswordResult.equals("Invalid PinCode")) {
                            Toast.makeText(ForgotPassword.this, "Wrong PinCode", Toast.LENGTH_SHORT).show();
                            System.out.println("22222222222222222222222");
                        } else if (resetPasswordResult.equals("Password Changed")) {
                            System.out.println("333333333333333333333333");
                            Toast.makeText(ForgotPassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(ForgotPassword.this, "Both password must be same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forgotLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}