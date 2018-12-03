package com.example.mtyso.mru_navigation;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import static com.example.mtyso.mru_navigation.MapsActivity.mMap;
import static com.example.mtyso.mru_navigation.MapsActivity.userHistory;
import java.util.Arrays;


public class PopupScreen_Explore extends ListActivity {
    private LocationAccessLayer loc = new LocationAccessLayer();
    //public ArrayList<LocationInstance> tmp;
    public String[] hallways = new String[loc.getAllHalls().size()];
    public String[] POIs = new String[loc.getAllPois().size()];
    public String[] lots = new String[loc.getAllParkingLots().size()];
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Build an array of hallways from the stored json strings.
        String[] JSONhallway = getResources().getStringArray(R.array.hallwayLocations);
        String[] JSONPOIs = getResources().getStringArray(R.array.poi);
        String[] JSONlots = getResources().getStringArray(R.array.pLots);

        buildOutputLists(JSONhallway);
        buildOutputLists(JSONPOIs);
        buildOutputLists(JSONlots);
        Arrays.sort(POIs);
        Arrays.sort(lots);
        Arrays.sort(hallways);
        setContentView(R.layout.explore);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                POIs);
        setListAdapter(adapter);
        }

        public void buildOutputLists(String[] listToConvert){
            int i=0;
            for (String json : listToConvert){
                JSONObject obj;
                try {
                    obj = new JSONObject(json);
                    if(obj.get("id").toString().equalsIgnoreCase("hall")){
                        this.hallways[i] = obj.get("name").toString().replaceAll("_"," ");
                    } else if(obj.get("id").toString().equalsIgnoreCase("poi")){
                        this.POIs[i] = obj.get("name").toString().replaceAll("_"," ");;
                    } else {
                        this.lots[i] = obj.get("name").toString().replaceAll("_"," ");;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }

        }
        //CustomAdapter adapter1=new CustomAdapter(this, hallways);
        //setListAdapter(adapter1);



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String item =(String) getListAdapter().getItem(position);
        try {
            LocationInstance destination = loc.getLocation(item);
            mMap.addMarker(new MarkerOptions().position(destination.getLocation()).title(destination.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(destination.getLocation()));
            userHistory.add(destination.getName());
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
//        public void onClickHallBtn(View v){
//            adapter=new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1,
//                    hallways);
//            setListAdapter(adapter);
//        }
//
//    public void onClickPOIBtn(View v){
//        adapter=new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                POIs);
//        setListAdapter(adapter);
//    }
//
//    public void onClickPlotBtn(View v){
//        adapter=new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                lots);
//        setListAdapter(adapter);
//    }

//        public String[] addToArray(ArrayList<LocationInstance> val){
//       String[] arr = new String[val.size()];
//        for(int i=0; i<val.size();i++){
//            arr[i] = val.get(i).getName();
//        }
//        return arr;
//    }

    public void onClick(View view) {
        String tag = view.getTag().toString();
        String[] array;
        if(tag.equals("hall")){
            array = hallways;
        } else if( tag.equals("poi")){
            array = POIs;
        } else {
            array = lots;
        }
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                array);
        setListAdapter(adapter);



    }
}

