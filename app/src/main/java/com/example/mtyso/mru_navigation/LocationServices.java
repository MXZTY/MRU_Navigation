package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


public class LocationServices {

    //Map Info
    private final float longRightBound = -114.123353f;
    private final float longLeftBound = -114.138005f;
    private final float latUpperBound = 51.015560f;
    private final float latLowerBound = 51.007944f;
    private DataHandler1 handle;

    // this.mruHallways = new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","O","Q","R","S","T","U","V","W","X","Y","Z","EA","EB","EC","ED","EL"};
    //the below needs refinement and is temporary

    public LocationServices(){
        this.handle = DataHandler1.getInstance();
    }

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

//    private void addLocation(LocationInstance location){
//        handle.add(location.getName().toLowerCase(), location);
//    }

    public LocationInstance getLocation(String userInput) throws Exception {
       if(verifyLocation(userInput)){
           return handle.get(userInput);
       } else {
           throw new Exception("Location Not Found");
       }
    }

    public boolean verifyLocation(String userInput){
        return handle.find(userInput);
    }

    public void printTable() {
        handle.printMap();
    }

    public long getSize() {
        return handle.size();
    }

    public ArrayList<LocationInstance> getAllHalls() {
        return handle.getAll("hall");
    }

    public ArrayList<LocationInstance> getAllPois() {
        return handle.getAll("poi");
    }

    public ArrayList<LocationInstance> getAllParkingLots(){
        return handle.getAll("plot");
    }
}
