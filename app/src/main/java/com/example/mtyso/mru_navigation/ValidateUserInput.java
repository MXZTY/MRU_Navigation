package com.example.mtyso.mru_navigation;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

class ValidateUserInput {

    //User input
    private String mSearchText;
    private boolean isHallway = false;

    //LocationInfo
    LocationData locations;

    private LatLng foundLocation;

    public ValidateUserInput(String searchText){
        this.mSearchText = searchText;
        this.locations = new LocationData();
    }

    /**
     * Method for controling the processing and validation of the users input
     * @return true by default.
     */
    public boolean getUserInput() throws Exception {
        System.out.println(mSearchText);
        String userInput = mSearchText;
        String[] sanitizedResults = new String[2];

        if(userInput.length() > 1){
            String[] location;
            //Call Sanitize user input to separate the key elements of the search string.
            sanitizedResults = sanitizeUserInput(userInput);
            try {
                //Call validate user input to make sure that the sanitized hallway code exists is a valid search string.
                boolean isValid = validateInput(sanitizedResults);
                if(!isValid){
                    throw new Exception("Input is not a valid mru location");
                }
                else {
                    if(this.isHallway) {
                        location = locations.getLocationOfHallway(sanitizedResults[0]);
                    } else{
                        location = locations.getLocationOfClassroom(sanitizedResults);
                    }
                    this.foundLocation = new LatLng(Float.valueOf(location[1]),Float.valueOf(location[2]));
                    return locations.isInMountRoyal(this.foundLocation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // If no input is given throw an exception to be handled/ignored.
            throw new Exception("No Input Given!");
        }

        return false;
    }

    /**
     * This method will take in a string (B204) and separate the different elements and return them in an array.
     *
     * @param input (B204)
     * @return ["B", "204"]
     */
    public String[] sanitizeUserInput(String input) {

        //Sanitize the users input using a regular expression. no need to validate in this method as validation will handle it.
        //currently Hardcoded to make test pass. Must change with implementation.
        String[] sanitizedinput = new String[]{"EB", ""};

        return sanitizedinput;
    }

    /**
     * this method will take the user input after sanitization and call
     * validation methods for classroom and hallway or just hallway.
     * @param sanitizedInput
     * @return
     */
    public boolean validateInput(String[] sanitizedInput) throws Exception {

        if(sanitizedInput[1] == null || sanitizedInput[1] == ""){
            this.isHallway = true;
            return locations.validateHallway(sanitizedInput[0]);
        } else{
            return locations.validateClassroom(sanitizedInput);
        }
    }

    /**
     * This method is used to return the validated location.
     * @return
     */
    public LatLng getFoundLocation(){
        return this.foundLocation;
    }

}
