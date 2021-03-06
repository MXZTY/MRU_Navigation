package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

public class Hallway implements LocationInstance {

    private String id;
    private String name;
    private LatLng location;

    public Hallway(String name, LatLng storedLocation, String id ){
        this.name = name;
        this.location = storedLocation;
        this.id = id;
    }

    /**
     * This method should return the location as a LatLng object.
     *
     * @return
     */
    @Override
    public LatLng getLocation() {
        return this.location;
    }

    /**
     * This method should return the latitude in string format
     *
     * @return
     */
    @Override
    public double getLatitude() {
        return this.location.latitude;
    }

    /**
     * This method should return the longitude in string format.
     *
     * @return
     */
    @Override
    public double getLongitude() {
        return this.location.longitude;
    }

    /**
     * This method is used to retrieve the name of the location instance.
     *
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * This method is used to get the ID of the location instance.
     *
     * @return
     */
    @Override
    public String getID() {
        return this.id;
    }


}
