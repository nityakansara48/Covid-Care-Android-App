package com.project.covidcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    EditText homeEmail;
    TabLayout tabLayout;
    ViewPager viewPager;
    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeEmail = (EditText) findViewById(R.id.homeEmail);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
            email = extras.getString("email");
            System.out.println(name);
            System.out.println(email);
            homeEmail.setText("Welcome "+name+",");
        }

        tabLayout = (TabLayout) findViewById(R.id.homeTabLayout);
        viewPager = (ViewPager) findViewById(R.id.homeViewPager);

        tabLayout.setupWithViewPager(viewPager);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentAdapter.addFragment(new HomeLogin(), "Home");
        fragmentAdapter.addFragment(new CovidZone(), "Covid Zone");
        fragmentAdapter.addFragment(new Services(), "Request Services");
        fragmentAdapter.addFragment(new UpdateCovidStatus(), "Update COVID Status");
        fragmentAdapter.addFragment(new Profile(), "Profile");
        if (email.equals("admin_nitya@gmail.com")){
            fragmentAdapter.addFragment(new ManageCovidService(), "Manage COVID Services");
        }

        viewPager.setAdapter(fragmentAdapter);
    }
}