package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

public class LocationData {

    //Map Info
    private final float longRightBound = -114.123353f;
    private final float longLeftBound = -114.138005f;
    private final float latUpperBound = 51.015560f;
    private final float latLowerBound = 51.007944f;
    private String[][] hallwayLocation;


    //todo - need to get correct location data for all of the hallways listed below.
    // this.mruHallways = new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","O","Q","R","S","T","U","V","W","X","Y","Z","EA","EB","EC","ED","EL"};
    //the below needs refinement and is temporary

    public LocationData(){
        this.hallwayLocation = new String[][]{
                {"EB", "51.010972", "-114.131208"},
                {"EA", "51.012276", "-114.129124"},
                {"EC", "51.011072", "-114.129473"},
                {"B", "51.012210", "-114.130732"},
                {"C", "51.012181", "-114.131277"},
                {"D", "51.012509", "-114.131426"},
                {"E", "51.012228", "-114.131829"}
        };
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
     * todo take in hallway string and search through the list of locations
     * This method ensures that the user input is a valid hallway code for MRU
     * @param input
     * @return true if valid input
     * @throws Exception e if invalid input.
     */
    public boolean validateHallway(String input) throws Exception {

        //method must take in input and test it against a list of hallway codes to make sure it is valid.
        // if the input is invalid, it must throw an exception e of invalid input.
        if(input.equalsIgnoreCase("Bad Input")){
            throw new Exception("Invalid Input");
        }
        return true;
    }

    /**
     * todo take in the array and search for classroom within the list of classrooms
     * this method will validate a classroom and hallway input exists for MRU
     * @param sanitizedInput
     * @return
     */
    public boolean validateClassroom(String[] sanitizedInput) throws Exception {

        if(sanitizedInput[0].equalsIgnoreCase("Bad Input")){
            throw new Exception("Invalid Input");
        }
        return true;
    }


    /**
     * todo takes in the classroom and returns its location
     * this method will search for a classroom and return its associated location data.
     * @param sanitizedResults
     * @return
     */
    public String[] getLocationOfClassroom(String[] sanitizedResults) {

        //change this later.
        String[] classroomLocation = new String[]{"EB","51.010972", "-114.131208"};
        return classroomLocation;
    }


    /**
     * todo takes in a hallway code and returns its location
     * Get the location data of a hallway code that is passed in.
     * @param b hallway code
     * @return hallway location array
     */
    public String[] getLocationOfHallway(String b) {

        //this method will be called by the userInput method and return the hallway location
        String[] hallwayLocation = new String[]{"EB","51.010972", "-114.131208"};
        return hallwayLocation;
    }

}
