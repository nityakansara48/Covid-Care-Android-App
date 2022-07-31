package com.project.covidcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, rePassword, name, contact, address, city, pinCode, covidDate;
    String covidStatus;
    Button btnSignup, btnLogin;
    DBConnection DB;
    Spinner covidStatusSpinner;
    String[] status = {"Positive", "Negative"};

    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.repassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        covidStatusSpinner = (Spinner) findViewById(R.id.covidStatus);
        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        pinCode = (EditText) findViewById(R.id.pinCode);
        covidDate = (EditText) findViewById(R.id.covidDate);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        covidStatusSpinner.setAdapter(adapter);



        DB = new DBConnection(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String signEmail = email.getText().toString();
                String signPass = password.getText().toString();
                String signRepass = rePassword.getText().toString();
                String signName = name.getText().toString();
                String signContact = contact.getText().toString();
                String signAddress = address.getText().toString();
                String signCity = city.getText().toString();
                String signPinCode = pinCode.getText().toString();
                String signDate = covidDate.getText().toString();
                Date date= null;
                try {
                    date = format.parse(signDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                covidStatus = covidStatusSpinner.getSelectedItem().toString();



                System.out.println(signName);
                System.out.println(signContact);
                System.out.println(signAddress);
                System.out.println(signCity);
                System.out.println(signPinCode);
                System.out.println(covidStatus);
                System.out.println(date);


                if(signEmail.equals("")||signPass.equals("")||signRepass.equals(""))
                    Toast.makeText(SignUpActivity.this, "Please enter all details.",Toast.LENGTH_SHORT).show();
                else{
                    if(signPass.equals(signRepass)){
                        Boolean chkUser = DB.checkUserName(signEmail);
                        if (chkUser==false){
                            Boolean insert = DB.insertData(signEmail, signPass, signName, signContact, signAddress, signCity, signPinCode, covidStatus, date.toString());
                            System.out.println(signEmail);
                            System.out.println(signPass);
                            System.out.println(insert);
                            if (insert==true) {
                                Toast.makeText(SignUpActivity.this, "Sign up Successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("email",signEmail);
                                intent.putExtra("name",signName);
                                SharedData sharedData = new SharedData(signEmail, signName);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(SignUpActivity.this, "Invalid Formats.",Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(SignUpActivity.this, "User already exists! Please Sign in.",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(SignUpActivity.this, "Both password must be same.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}