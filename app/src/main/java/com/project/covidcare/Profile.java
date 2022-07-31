package com.project.covidcare;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Profile extends Fragment {

    private String email = SharedData.getEmail();
    private String name = SharedData.getName();
    TextView userName;
    TextView userEmail;
    TextView userContact;
    TextView userAddress;
    TextView userCity;
    TextView userZipCode;
    TextView userCovidStatus;
    TextView userReportDate;
    Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        DBConnection DB = new DBConnection(getActivity());

        List<String> userData = DB.getAllData(email);

        userName = (TextView) view.findViewById(R.id.userName);
        userName.setText(name);
        userEmail = (TextView) view.findViewById(R.id.userEmail);
        userEmail.setText(email);
        userContact = (TextView) view.findViewById(R.id.userContact);
        userContact.setText(userData.get(2));
        userAddress = (TextView) view.findViewById(R.id.userAddress);
        userAddress.setText(userData.get(3));
        userCity = (TextView) view.findViewById(R.id.userCity);
        userCity.setText(userData.get(4));
        userZipCode = (TextView) view.findViewById(R.id.userZipCode);
        userZipCode.setText(userData.get(5));
        userCovidStatus = (TextView) view.findViewById(R.id.userCovidStatus);
        userCovidStatus.setText(userData.get(6));
        userReportDate = (TextView) view.findViewById(R.id.userReportDate);
        userReportDate.setText(userData.get(7));

        System.out.println(userData);

        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.resetData();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}