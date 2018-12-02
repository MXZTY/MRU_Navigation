package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import static android.icu.lang.UCharacter.toUpperCase;

public class DataHandler1 implements Serializable {

    // The hash map that will be used as a singleton instance.
    private HashMap<Long, LocationInstance> map = new HashMap<Long, LocationInstance>();

    //Make the class variable static and volatile so it may be accessed in a static format.
    private static volatile DataHandler1 ourInstance = new DataHandler1();

    private DataHandler1() {
        if(ourInstance != null){
            //the instance has has already been initialized and cannot create a new one call getInstance() to get current instance.
            throw new RuntimeException("Use getInstance() method to get the single instance of this class");
        }
    }

    /**
     * This method is used to return the classes instance once it has been created.
     * This allows us to make calls to the same hash map from many different points within the application.
     * @return ourInstance will represent the single instance.
     */
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

    /**
     * This method is used to add location instances to the hash map.
     * It takes in the locationInstances name as a key, and the location instance as the value.
     * The name(key) must be hashed before being added to the hash map.
     * @param key location instances name
     * @param value the location instance that you wish to add. (hallway, parking lot, or point of interest. )
     */
    public void add(String key, LocationInstance value){
        this.map.put(hashedValue(key), value);
    }

    /**
     * This method is used to find a key that should exist within the hash map.
     * this method will take in a locationInstances name as the kay, hash the value and search the hash map for the key.
     * @param key location instances name or search string.
     * @return boolean based on if it was found or not.
     */
    public boolean find(String key){
        if(this.map.get(hashedValue(key))!= null){
            return true;
        }
        else return false;
    }

    /**
     * This method is used to retrieve a location instance based on the key
     * @param key the location instances name or search string.
     * @return the location instance associated with the key.
     */
    public LocationInstance get(String key){
        return this.map.get(hashedValue(key));
    }

    /**
     * This method returns an array list of all the locations that belong to a specific id (hall, poi, pLot);
     * @param id must be hall, poi or pLot
     * @return the values that share the id that was passed in to the method.
     */
    private ArrayList<LocationInstance> getById(String id){
        ArrayList<LocationInstance> locations = new ArrayList<LocationInstance>();
        int i = 0;
        for(LocationInstance location : map.values()){
            if(location.getID() == id){
                locations.add( location);
                i++;
            }
        }
        return locations;
    }

    /**
     * This method is used to visualize each item within the hash map
     * and is mainly used for debugging purposes if there is an issue with the hash map
     */
    public void printMap(){
        System.out.println("printing the map");
        Set<Long> keys = map.keySet();
        Iterator<Long> iter = keys.iterator();
        while(iter.hasNext()){
            Long key = iter.next();
            String value = map.get(key).getName();
        }

    }

    /**
     * This method must be called on start up with all the location instances wished to be stored in the hash map
     * it will be called from main activity with all locations within a string array in json format.
     * @param locations Must be a string array in json format symbolizing all of the locations to store in the hash map.
     */
    public void buildTable(String[] locations){
        LocationInstance location;
// for each json string, parse the string and create a hallway object.
        for(String jsonStr : locations){
            try {
                JSONObject obj = new JSONObject(jsonStr);
                if(obj.get("id").toString().equalsIgnoreCase("hall")){
                    location = new Hallway(obj.get("name").toString(), new LatLng((double)obj.get("lat"), (double)obj.get("lng")), obj.get("id").toString());
                } else if(obj.get("id").toString().equalsIgnoreCase("poi")){
                    location = new PointOfInterest(formatText(obj.get("name").toString()),  new LatLng((double)obj.get("lat"), (double)obj.get("lng")), obj.get("id").toString());
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
        word = formatText(word);
        long hashVal = 0;
        for (int i = 0; i < word.length(); i++) {
            hashVal = (hashVal * 31) + word.charAt(i);
        }
        if (hashVal < 0) {
            hashVal = hashVal * -1;
        }
        return hashVal;
    }

    /**
     * This method is used to return the hash maps size.
     * @return the hash maps size.
     */
    public long size() {
        return map.size();
    }

    /**
     * This method is used to get all intances of a specific hallway type
     * TODO (Revisit when getByID is functioning to see if we need this method. )
     * @param objectType
     * @return
     */
    public ArrayList<LocationInstance> getAll(String objectType){
        return this.getById(objectType);
    }

    //public ArrayList<Hallway> getAllHallways(){return this.getAllHallways();}

    /**
     * This method ensures the proper formatting before hashing the string into a key value.
     * This will ensure that inconsistent input with special characters, capitals, spaces etc,
     * will result in the same key as long as the characters are the same
     * @param textToFormat
     * @return
     */
    public String formatText(String textToFormat){ return textToFormat.replaceAll("[^A-Za-z]+", "").toLowerCase(); }


}
