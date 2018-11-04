package com.example.mtyso.mru_navigation;

public class DataHandler {

    // this is an array containing all of the hallways with their associated GPS locations.
    private String[][] hallwayLocations = new String[][]{
        {"A","51.011502","-114.130802"},
        {"B","51.012434","-114.130541"},
        {"C","51.0121996","-114.1313158"},
        {"D","51.0123245","-114.1316719"},
        {"E","51.0119611","-114.1320615"},
        {"F","51.0123726","-114.1321011"},
        {"G","51.0129634","-114.1315663"},
        {"H","51.0125063","-114.1319602"},
        {"I","51.0121855","-114.1327585"},
        {"J","51.0130598","-114.1332396"},
        {"K","51.0132223","-114.1329748"},
        {"M","51.0129179","-114.1322207"},
        {"N","51.0132630","-114.1322040"},
        {"O","51.0134167","-114.1318496"},
        {"Q","51.0122429","-114.1314007"},
        {"R","51.0129774","-114.1321121"},
        {"S","51.0124112","-114.1331019"},
        {"T","51.0135849","-114.1316468"},
        {"U","51.0127983","-114.1327216"},
        {"V","51.0132010","-114.1320578"},
        {"W","51.0128162","-114.1331977"},
        {"X","51.0133800","-114.1325912"},
        {"Y","51.0132307","-114.1330673"},
        {"Z","51.0137926","-114.1326311"},
        {"EB","51.010972", "-114.131208"},
        {"EA","51.012276", "-114.129124"},
        {"EC","51.011072", "-114.129473"},
        {"ED","51.0114674","-114.1285921"},
        {"EL","51.0121927","-114.1279168"}};

    /**
     * This method recursively iterates the hallway locations array trying to match a hallway ID
     * If no hallway id is found it will return false.
     * @param hallID the ID of the hallway to find.
     * @param iterate the iteration of the current loop through the array. (always initialized with 0)
     * @return true or false based on if the hallway was found.
     */
    public boolean findHallway(String hallID, int iterate){
        if(iterate != hallwayLocations.length){
            if(hallID.equalsIgnoreCase((hallwayLocations[iterate][0]))) {
                return true;
            } else {
                return this.findHallway(hallID, iterate + 1);
            }
        }
        return false;
    }

    /**
     * This method recursively iterates the hallway locations array to retrieve a validated hallway code.
     * @param hallID the ID of the hallway to retrieve.
     * @param iterate the current iteration of looping through the array (always initialized with 0.)
     * @return the hallway location array found.
     */
    public String[] getHallwayLocation(String hallID, int iterate){
        if(hallID.equalsIgnoreCase(hallwayLocations[iterate][0])){
            return hallwayLocations[iterate];
        } else {
            return getHallwayLocation(hallID, iterate + 1);
        }
    }

    //ToDo
    public boolean findClassroom(String[] sanitized, int iterate){
        return false;
    }

    //ToDo
    public String[] getClassroomLocation(String[] sanitized, int iterate){

        return null;
    }



}
