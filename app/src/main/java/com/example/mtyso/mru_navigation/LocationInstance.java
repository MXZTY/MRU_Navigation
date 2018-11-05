package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

public interface LocationInstance {

    /**
     * This method should return the location as a LatLng object.
     * @return
     */
    LatLng getLocation();

    /**
     * This method should return the latitude in string format
     * @return
     */
    double getLatitude();

    /**
     * This method should return the longitude in string format.
     * @return
     */
    double getLongitude();

    /**
     * This method is used to retrieve the name of the location instance.
     * @return
     */
    String getName();

    /**
     * This method is used to get the ID of the location instance.
     * @return
     */
    int getID();

    void setID(int index);
}
