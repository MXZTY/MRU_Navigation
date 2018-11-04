package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

public class LocationServices {

    //Map Info
    private final float longRightBound = -114.123353f;
    private final float longLeftBound = -114.138005f;
    private final float latUpperBound = 51.015560f;
    private final float latLowerBound = 51.007944f;
    private DataHandler handle;

    //todo - need to get correct location data for all of the hallways listed below.
    // this.mruHallways = new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","O","Q","R","S","T","U","V","W","X","Y","Z","EA","EB","EC","ED","EL"};
    //the below needs refinement and is temporary

    public LocationServices(){
        this.handle = new DataHandler();
    }

    /**
     * todo finish after other methods are completed
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
     * This method ensures that the user input is a valid hallway code for MRU
     * @param input
     * @return true if valid input
     */
    public boolean validateHallway(String input) {

       return handle.findHallway(input, 0);
    }

    /**
     * todo take in the array and search for classroom within the list of classrooms
     * this method will validate a classroom and hallway input exists for MRU
     * @param sanitizedInput
     * @return
     */
    public boolean validateClassroom(String[] sanitizedInput) throws Exception {
        return handle.findClassroom(sanitizedInput, 0);
    }


    /**
     * todo takes in the classroom and returns its location
     * this method will search for a classroom and return its associated location data.
     * @param sanitizedInput
     * @return
     */
    public String[] getLocationOfClassroom(String[] sanitizedInput) {
        return handle.getClassroomLocation(sanitizedInput, 0);
    }


    /**
     * todo takes in a hallway code and returns its location
     * Get the location data of a hallway code that is passed in.
     * @param b hallway code
     * @return hallway location array
     */
    public String[] getLocationOfHallway(String b) {
        System.out.println("something wrong before this");
        return handle.getHallwayLocation(b, 0);

    }

}
