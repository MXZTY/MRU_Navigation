package com.example.mtyso.mru_navigation;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static android.icu.lang.UCharacter.toUpperCase;

public class DataHandler1 implements Serializable {

    private HashMap<Long, LocationInstance> map = new HashMap<Long, LocationInstance>();

    private static volatile DataHandler1 ourInstance = new DataHandler1();

    private DataHandler1() {
        if(ourInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class");
        }
    }

    public static DataHandler1 getInstance() {
        if(ourInstance == null){
            synchronized (DataHandler1.class){
                if (ourInstance == null) ourInstance = new DataHandler1();
            }
        }
        return ourInstance;
    }

    protected DataHandler1 readResolve(){
        return getInstance();
    }

    public void add(String key, LocationInstance value){
        this.map.put(hashedValue(key.toUpperCase()), value);
    }

    public boolean find(String key){
        if(this.map.get(hashedValue(key))!= null){
            return true;
        }
        else return false;
    }

    public LocationInstance get(String key){
        return this.map.get(hashedValue(key));
    }

    public void printMap(){
        System.out.println("printing the map");
        Set<Long> keys = map.keySet();
        Iterator<Long> iter = keys.iterator();
        while(iter.hasNext()){
            Long key = iter.next();
            String value = map.get(key).getName();
            System.out.println("\t"+key+"|\t"+map.get(key).getName());
        }

    }

    public void buildTable(String[] locations){
        LocationInstance location;
// for each json string, parse the string and create a hallway object.
        for(String jsonStr : locations){
            try {
                JSONObject obj = new JSONObject(jsonStr);
                if(obj.get("id").toString().equalsIgnoreCase("hall")){
                    location = new Hallway(obj.get("name").toString(), new LatLng((double)obj.get("lat"), (double)obj.get("lng")), obj.get("id").toString());
                } else if(obj.get("id").toString().equalsIgnoreCase("poi")){
                    location = new PointOfInterest(obj.get("name").toString().replaceAll("_", "\\s+"),  new LatLng((double)obj.get("lat"), (double)obj.get("lng")), obj.get("id").toString());
                } else {
                    location = new ParkingLot(obj.get("name").toString(),  new LatLng((double)obj.get("lat"), (double)obj.get("lng")), obj.get("id").toString(), false, false );
                }
                // add the hallway object to the hash table.
                add(location.getName(), location);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * method for hashing the user input and for setting the id of the location instances.
     * @param word
     * @return
     */
    public Long hashedValue(String word) {
        long hashVal = 0;
        for (int i = 0; i < word.length(); i++) {
            hashVal = (hashVal * 31) + word.charAt(i);
        }
        if (hashVal < 0) {
            hashVal = hashVal * -1;
        }
        return hashVal;
    }

    public long size() {
        return map.size();
    }
}
