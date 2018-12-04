package com.example.mtyso.mru_navigation;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.mtyso.mru_navigation.MapsActivity.mMap;
import static com.example.mtyso.mru_navigation.MapsActivity.userHistory;

public class PopupScreen_Favourites extends ListActivity {
    LocationAccessLayer loc = new LocationAccessLayer();
    String[] favs = new String[MapsActivity.userFavourites.size()];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);
        for(int i = 0; i < MapsActivity.userFavourites.size(); i++){
            favs[i] =  MapsActivity.userFavourites.get(i);

        }
        CustomAdapter adapter=new CustomAdapter(this, favs);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String item=(String) getListAdapter().getItem(position);
        item = item.replaceAll("_"," ");
        try {
            LocationInstance destination = loc.getLocation(item);
            mMap.addMarker(new MarkerOptions().position(destination.getLocation()).title(destination.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(destination.getLocation()));
            if(!userHistory.contains(item)){userHistory.add(item);};
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
