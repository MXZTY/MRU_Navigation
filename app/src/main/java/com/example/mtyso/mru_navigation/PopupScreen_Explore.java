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
    private LocationAccessLayer loc = new LocationAccessLayer();
    //public ArrayList<LocationInstance> tmp;
    public String[] hallways;
    public String[] POIs;
    public String[] lots;
    ArrayList<LocationInstance> h;
    ArrayList<LocationInstance> p;
    ArrayList<LocationInstance> l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hallways = addToArray(loc.getAllHalls());
        POIs = addToArray(loc.getAllParkingLots());
        lots = addToArray(loc.getAllPois());

        CustomAdapter adapter1=new CustomAdapter(this, hallways);
        setListAdapter(adapter1);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String item =(String) getListAdapter().getItem(position);


    }

    public String[] addToArray(ArrayList<LocationInstance> val){
       String[] arr = new String[val.size()];
        for(int i=0; i<val.size();i++){
            arr[i] = val.get(i).getName();
        }
        return arr;
    }

}

