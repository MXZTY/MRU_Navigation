package com.example.mtyso.mru_navigation;

import android.app.ListActivity;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class PopupScreen_Explore extends ListActivity {
    //private LocationAccessLayer loc = new LocationAccessLayer();
    //public ArrayList<LocationInstance> tmp;
    //public String[] hallways;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //CustomAdapter adapter=new CustomAdapter(this, hallways);
        //setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String item=(String) getListAdapter().getItem(position);
    }
}

