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

import java.util.ArrayList;

public class PopupScreen_Favourites extends ListActivity {

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
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String item=(String) getListAdapter().getItem(position);
    }

    public void addToFavorites(View view) {
        ///
    }
}
