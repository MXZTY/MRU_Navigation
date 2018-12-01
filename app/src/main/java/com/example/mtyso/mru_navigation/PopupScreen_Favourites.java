package com.example.mtyso.mru_navigation;
import android.app.Activity;
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

public class PopupScreen_Favourites extends AppCompatActivity {

    public ArrayList<String> favs = MapsActivity.userFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        ListView historyList = (ListView) findViewById(R.id.textView2);
        final ArrayAdapter listAdapter;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = (dm.heightPixels / 2);
        getWindow().setLayout(width,height);
        
        listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,favs);
        historyList.setAdapter(listAdapter);




        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                //Insert code here for onClick
            }
        });
    }
}