package com.example.mtyso.mru_navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DataHandler1 handle = DataHandler1.getInstance();
        LocationAccessLayer service = new LocationAccessLayer();

        // Build an array of hallways from the stored json strings.
        String[] hallArray = getResources().getStringArray(R.array.hallwayLocations);

        //still need correct locations recorded.
        String[] poiArray = getResources().getStringArray(R.array.poi);

        String[]parkArray = getResources().getStringArray(R.array.pLots);


        //Add in parking lots once locations are recorded.
//        String[] parkingLotArray = getResources().getStringArray(R.array.parkingLots);

        service.buildTable(hallArray);
        System.out.println("SIZE AFTER HALLWAYS ADDED!    " + service.getSize());
        service.buildTable(poiArray);
        System.out.println("SIZE AFTER POI ADDED!    " + service.getSize());
        service.buildTable(parkArray);
        System.out.println("SIZE AFTER lots ADDED!    " + service.getSize());

        setContentView( R.layout.activity_home_screen );
    }

    protected void startNavScreen(View view){
        Intent startMapsActivity = new Intent(this, MapsActivity.class);
        startActivity(startMapsActivity);
    }
}
