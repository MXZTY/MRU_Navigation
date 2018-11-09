package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * This class is meant to act as a layer of data abstraction and will provide the application with
 * an access layer so each activity class can make queries to the dataHandler (singleton hash map)
 * without having to access the singleton directly.
 *
 */
public class LocationAccessLayer {

    //Map Info
    private final float longRightBound = -114.123353f;
    private final float longLeftBound = -114.138005f;
    private final float latUpperBound = 51.015560f;
    private final float latLowerBound = 51.007944f;
    private DataHandler1 handle;

    /**
     * Instantiate the data handler when this class is called.
     */
    public LocationAccessLayer(){
        this.handle = DataHandler1.getInstance();
    }

    /**
     * Call build table with the string array passed in.
     * @param arrayToAdd
     */
    public void buildTable(String[] arrayToAdd){
        handle.buildTable(arrayToAdd);
    }

    /**
     * Method for controling the processing and validation of the users input
     * @return true by default.
     */
    public boolean validateUserInput(String mSearchText) throws Exception {
        System.out.println(mSearchText);
        mSearchText = mSearchText.toUpperCase();

        if(mSearchText.length() >= 1){
            try {
                return isInMountRoyal(getLocation(mSearchText).getLocation());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            // If no input is given throw an exception to be handled/ignored.
            throw new Exception("No Input Given!");
        }

    }

    /**
     * Veryfies that the location attached to the hallway/classroom is within MRU campus grounds.
     * @param latlng
     * @return
     */
    public boolean isInMountRoyal(LatLng latlng) {
        if(latlng.latitude < latUpperBound && latlng.latitude > latLowerBound){
            if(latlng.longitude > longLeftBound && latlng.longitude < longRightBound){
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to return a location from the dataHandler singleton.
     * @param userInput search string
     * @return the location associated with the search
     * @throws Exception input may not be found in the hash map.
     */
    public LocationInstance getLocation(String userInput) throws Exception {
       if(verifyLocation(userInput)){
           return handle.get(userInput);
       } else {
           throw new Exception("Location Not Found");
       }
    }

    /**
     * This metthod ensures that the location can be found within the data handler hash map
     * @param userInput
     * @return
     */
    public boolean verifyLocation(String userInput){
        return handle.find(userInput);
    }

    /**
     * This method is used to print the hash table (mainly for debugging purposes. )
     */
    public void printTable() {
        handle.printMap();
    }

    /**
     * This method will return the size of the hash map.
     * @return
     */
    public long getSize() {
        return handle.size();
    }

    /**
     * TODO This method is used to return all the hallways within MRU
     * @return
     */
    public ArrayList<LocationInstance> getAllHalls() {
        return handle.getAll("hall");
    }

    //todo implement getAll so that the bottom nav menu can access all poi information and display it in the tray
    public ArrayList<LocationInstance> getAllPois() {
        return handle.getAll("poi");
    }

    //todo implement getAll so that the bottom nav menu can access all parking lot information and display it in the tray
    public ArrayList<LocationInstance> getAllParkingLots(){
        return handle.getAll("plot");
    }
}
