package com.example.mtyso.mru_navigation;

import android.app.ListActivity;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hallways = addToArray(loc.getAllHalls());
        POIs = addToArray(loc.getAllParkingLots());
        lots = addToArray(loc.getAllPois());
        setContentView(R.layout.explore);

        }

        //CustomAdapter adapter1=new CustomAdapter(this, hallways);
        //setListAdapter(adapter1);



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String item =(String) getListAdapter().getItem(position);


    }
    
        public void onClickHallBtn(View v){
            adapter=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    hallways);
            setListAdapter(adapter);
        }

    public void onClickPOIBtn(View v){
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                POIs);
        setListAdapter(adapter);
    }

    public void onClickPlotBtn(View v){
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                lots);
        setListAdapter(adapter);
    }

        public String[] addToArray(ArrayList<LocationInstance> val){
       String[] arr = new String[val.size()];
        for(int i=0; i<val.size();i++){
            arr[i] = val.get(i).getName();
        }
        return arr;
    }

}

