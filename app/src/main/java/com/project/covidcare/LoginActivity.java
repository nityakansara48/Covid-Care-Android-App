package com.project.covidcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    TextView forgotPassword;
    Button btnSignup, btnLogin;
    DBConnection DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup1);
        btnLogin = (Button) findViewById(R.id.btnLogin1);

        DB = new DBConnection(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginEmail = email.getText().toString();
                String loginPass = password.getText().toString();

                if(loginEmail.equals("")||loginPass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all fields.", Toast.LENGTH_LONG).show();
                else{
                    Boolean checkUserPass = DB.checkUserNamePassword(loginEmail, loginPass);
                    if (checkUserPass==true){
                        String userName = DB.getUsername(loginEmail);
                        //Toast.makeText(LoginActivity.this, "Login Successfully.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("name",userName);
                        intent.putExtra("email",loginEmail);
                        SharedData sharedData = new SharedData(loginEmail, userName);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}