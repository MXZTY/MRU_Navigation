package com.example.mtyso.mru_navigation;

import android.location.Location;

public class DataHandler {

    private HashTable table;

    public DataHandler(HashTable table){
        this.table = table;
    }

    /**
     * This method recursively iterates the hallway locations array trying to match a hallway ID
     * If no hallway id is found it will return false.
     * @param hallID the ID of the hallway to find.
     * @return true or false based on if the hallway was found.
     */
    public boolean findHallway(String hallID){
        return table.find(hallID);
    }

    /**
     * This method recursively iterates the hallway locations array to retrieve a validated hallway code.
     * @param hallID the ID of the hallway to retrieve.
     * @return the hallway location array found.
     */
    public LocationInstance getHallwayLocation(String hallID){
        return table.getLocationObject(hallID);
    }

    //ToDo
    public boolean findClassroom(String[] sanitized){
        return false;
    }

    //ToDo
    public LocationInstance getClassroomLocation(String[] sanitized){

        return null;
    }



}
