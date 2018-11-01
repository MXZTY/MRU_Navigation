package com.example.mtyso.mru_navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    protected void startNavScreen(View view){
        Intent startMapsActivity = new Intent(this, MapsActivity.class);
        startActivity(startMapsActivity);
    }
}
