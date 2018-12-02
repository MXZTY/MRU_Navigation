package com.example.mtyso.mru_navigation;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PopupScreen_History extends ListActivity {

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
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        String item=(String) getListAdapter().getItem(position);
        MapsActivity.userFavourites.add(item);
        Toast.makeText(getApplicationContext(),item+" has been added to your favourites!", Toast.LENGTH_SHORT).show();
    }
}
