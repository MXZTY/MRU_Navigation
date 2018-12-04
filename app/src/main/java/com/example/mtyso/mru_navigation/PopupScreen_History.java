package com.example.mtyso.mru_navigation;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.mtyso.mru_navigation.MapsActivity.mMap;
import static com.example.mtyso.mru_navigation.MapsActivity.userHistory;

public class PopupScreen_History extends ListActivity {
    LocationAccessLayer loc = new LocationAccessLayer();
    String[] languages = new String[MapsActivity.userHistory.size()];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);
        for(int i = 0; i < MapsActivity.userHistory.size(); i++){
            languages[i] =  MapsActivity.userHistory.get(i);
        }
        CustomAdapter adapter=new CustomAdapter(this, languages);
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

    public void addToFavorites(View view) {
        View parentRow = (View) view.getParent();
        ListView list = (ListView) parentRow.getParent();
        final int position = list.getPositionForView(parentRow);
        System.out.println(position);
        String item=(String) getListAdapter().getItem(position);
        item = item.replaceAll("_"," ");
        try {
            LocationInstance destination = loc.getLocation(item);
            mMap.addMarker(new MarkerOptions().position(destination.getLocation()).title(destination.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(destination.getLocation()));
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(MapsActivity.userFavourites.contains(item)){
            //dont add, already in favorites
        } else {
            MapsActivity.userFavourites.add(item);
            Toast.makeText(getApplicationContext(), item + " has been added to your favourites!", Toast.LENGTH_SHORT).show();
        }
    }

}
