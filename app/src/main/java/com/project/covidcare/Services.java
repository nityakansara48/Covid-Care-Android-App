package com.project.covidcare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Services extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_services, container, false);

        String[] status = {"COVID Vaccine", "COVID Testing Kit", "Health Care Kit"};
        Spinner COVIDServiceSpinner = (Spinner) view.findViewById(R.id.COVIDServiceSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, status);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        COVIDServiceSpinner.setAdapter(adapter);

        EditText address1 = (EditText) view.findViewById(R.id.addressLine1);
        EditText address2 = (EditText) view.findViewById(R.id.addressLine2);
        EditText city = (EditText) view.findViewById(R.id.city);
        EditText state = (EditText) view.findViewById(R.id.state);
        EditText zipcode = (EditText) view.findViewById(R.id.zipcode);



        Button btnPlaceOrder = (Button) view.findViewById(R.id.orderButton);


        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String orderAddress1 = address1.getText().toString();
                String orderAddress2 = address2.getText().toString();
                String orderCity = city.getText().toString();
                String orderState = state.getText().toString();
                String orderZip = zipcode.getText().toString();

                if (orderAddress1.equals("") || orderAddress2.equals("") || orderCity.equals("") || orderState.equals("") || orderZip.equals("")){
                    Toast.makeText(getActivity(), "Please enter all details.",Toast.LENGTH_SHORT).show();
                } else {
                    address1.setText("");
                    address2.setText("");
                    city.setText("");
                    state.setText("");
                    zipcode.setText("");
                    COVIDServiceSpinner.setSelection(0);

                    Toast.makeText(getActivity(), "Order has been placed Successfully.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}