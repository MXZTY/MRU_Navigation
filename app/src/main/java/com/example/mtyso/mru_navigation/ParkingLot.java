package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

public class ParkingLot implements LocationInstance {

    private String id;
    private String name;
    private LatLng location;
    private boolean isPaidLot;
    private boolean isAccesible;

    public ParkingLot(String name, LatLng storedLocation,String id, boolean paidLot, boolean accessibleParking ){
        this.name = name;
        this.location = storedLocation;
        this.id = id;
        this.isPaidLot = paidLot;
        this.isAccesible = accessibleParking;
    }

    /**
     * This method should return the location as a LatLng object.
     *
     * @return
     */
    @Override
    public LatLng getLocation() {
        return null;
    }

    /**
     * This method should return the latitude in string format
     *
     * @return
     */
    @Override
    public double getLatitude() {
        return 0;
    }

    /**
     * This method should return the longitude in string format.
     *
     * @return
     */
    @Override
    public double getLongitude() {
        return 0;
    }

    /**
     * This method is used to retrieve the name of the location instance.
     *
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * This method is used to get the ID of the location instance.
     *
     * @return
     */
    @Override
    public String getID() {
        return null;
    }

    public boolean isPaidLot(){
        return this.isPaidLot;
    }

    public boolean isAccesible(){
        return this.isAccesible;
    }

}
