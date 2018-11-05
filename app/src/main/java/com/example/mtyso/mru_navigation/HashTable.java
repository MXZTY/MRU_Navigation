package com.example.mtyso.mru_navigation;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class HashTable {

    //Create a bucket large enough to hold all locations.
    private Bucket[] table = new Bucket[5000];

    /**
     * Adds a location instance to the hash tables bucket.
     * @param dataToAdd
     * @return
     */
    public boolean add(LocationInstance dataToAdd){
        int index = (int) (hashedValue(dataToAdd.getName()) % table.length);
        dataToAdd.setID(index);
        if (table[index] == null){
            Bucket bucketToAddTo = new Bucket();
            bucketToAddTo.add(dataToAdd);
            table[index] = bucketToAddTo;
            return true;
        } else {
            return table[index].add(dataToAdd);
        }
    }

    /**
     * Delete a location instance from the hash tables bucket.
     * @param dataToDelete
     */
    public void delete(LocationInstance dataToDelete) {
        int index = (int) (hashedValue(dataToDelete.getName()) % table.length);
        if (table[index] != null){
            table[index].remove(dataToDelete);
        }
    }

    /**
     * Find a location based on the input string
     * Traverse the hash bucket to see if the name is found.
     * @param userInput
     * @return
     */
    public boolean find(String userInput) {
        boolean found = false;
        int index = (int) (hashedValue(userInput) % table.length);
        if (table[index] != null){
            found = table[index].find(userInput);
        }
        return found;
    }

    /**
     * returns the location instance for the user input if found in the tree.
     * will return null if not found.
     * @param userInput
     * @return
     */
    public LocationInstance getLocationObject(String userInput){
        LocationInstance found = null;
        int index = (int) (hashedValue(userInput) % table.length);
        if (table[index] != null){
            found = table[index].getLocationData(userInput);
        }
        return found;
    }

    /**
     * method for hashing the user input and for setting the id of the location instances.
     * @param word
     * @return
     */
    public long hashedValue(String word) {
        long hashVal = 0;
        for (int i = 0; i < word.length(); i++) {
            hashVal = (hashVal * 31) + word.charAt(i);
        }
        if (hashVal < 0) {
            hashVal = hashVal * -1;
        }
        return hashVal;
    }

}
