package com.project.covidcare;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateCovidStatus extends Fragment {

    private String email = SharedData.getEmail();
    Spinner covidStatusUpdateSpinner;
    String[] statusUpdate = {"Positive", "Negative"};
    EditText updateDate;
    DBConnection DB;
    Button btnUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_covid_status, container, false);

        covidStatusUpdateSpinner = (Spinner) view.findViewById(R.id.covidStatusUpdate);
        updateDate = (EditText) view.findViewById(R.id.updateDate);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, statusUpdate);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        covidStatusUpdateSpinner.setAdapter(adapter);

        btnUpdate = (Button) view.findViewById(R.id.btnCovidStatusUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB = new DBConnection(getActivity());
                String covidStatusUpdate = covidStatusUpdateSpinner.getSelectedItem().toString();
                String covidStatusUpdateDate = updateDate.getText().toString();
                Boolean flagUpdate = DB.updateCovidStatus(email, covidStatusUpdate, covidStatusUpdateDate);
                if (flagUpdate == true){
                    Toast.makeText(getActivity(), "COVID Status Updated Successfully.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "COVID Status Not Updated Successfully.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}