package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLocationServices {

    //classes involved with testing this functional requirement.
    private MapsActivity activity;
    private LocationServices locationData;
    private String[] pois;

    //resources to use for test
    private String simulatedInput;
    private String[] mruHallways;
    private String[] pLots;

    @Before
    public void setUp() throws Exception {

        this.locationData = new LocationServices();
        this.activity = new MapsActivity();
        this.mruHallways = new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","O","Q","R","S","T","U","V","W","X","Y","Z","EA","EB","EC","ED","EL"};
        this.pLots = new String[]{
                "Lot 1",
                "Lot 2",
                "Lot 3",
                "Lot 4",
                "Lot 5",
                "Lot 6",
                "Lot 6a",
                "Lot 7",
                "Lot 8",
                "Lot 9",
                "East Gate Parkade",
        };
        this.pois = new String[]{
                "Triple Gymnasium",
                "Aquatic Center",
                "Fitness Center",
                "Recreation Customer Service",
                "Running Track",
                "Wyckham House",
                "Digital Print Center",
                "Main Street",
                "Registrars Office",
                "Admissions and Recruitment",
                "Kerby Hall",
                "Event And Theater Services",
                "Ross Glen Hall",
                "Atelier",
                "Box Office",
                "Bella Concert Hall",
                "Conservatory Services",
                "Parking Office",
                "Bookstore",
                "Student Digital Print Center",
                "Bisset School Of Business",
                "Faculty Of Arts",
                "Recreation Center",
                "East Entrance",
                "Roderick Mah Center for Continuous Learning",
                "Taylor Center for the Performing Arts",
                "Riddel Library and Learning Center",
                "West Entrance",
                "Wellness Center",
                "Child Care Center",
                "Leacock and Wright Theaters",
                "The Table",
                "Security Office",
                "TransAlta Pavilion",
                "Student Learning Services",
                "Lincoln Park Room",
                "Department of Education",
                "Continuing Education Customer Service"
        };

    }

    /**
     * This test will ensure that the user input, and the Json data is formatted correctly for adding and searching through the hash map
     * the formatting should get rid of all
     */
    @Test
    public void testTextFormattingForHashMap(){
//        assertEquals("teststringisateststring", locationData.formatText("Test_String is a TEST _ STRING"));
    }

    @Test
    public void itCanGetAllHallways(){
//        assertTrue(locationData.getAllHalls().length == mruHallways.length);
    }

    @Test
    public void itCanGetAllPOIs(){
//        assertTrue(pois.length == locationData.getAllPois().length);
    }

    @Test
    public void itCanGetAllParkingLots(){
//        assertTrue(pLots.length == locationData.getAllParkingLots().length);
    }

    /**
     * This will test that the hash map is built on application startup.
     */
    @Test
    public void testHashMapIsBuilt(){
        assertTrue(locationData.getSize()>0);
        assertTrue(locationData.getSize()>50);
    }


    /**
     * This test will ensure that all correct hallway codes can be looked up by the user.
      */
    @Test
    public void testCanLookUpHallway(){
        for (String hall: mruHallways){
            try {
                assertNotNull(locationData.getLocation(hall) );
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

    /**
     * This test ensures that all the correct POIs in MRU can be looked up
     */
    @Test
    public void testCanLookupPOI(){
        for (String poi: pois){
            try {
                assertNotNull(locationData.getLocation(poi.toLowerCase()) );
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

    /**
     * This test ensures that all the correct parking lot information can be looked up.
     */
    @Test
    public void testCanLookupParkingLots(){
        for (String pLot: pLots){
            try {
                assertNotNull(locationData.getLocation(pLot.toLowerCase()) );
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

//    /**
//     * todo need to change this test into 2 tests. one for hallway validation and one for classroom validation.
//     * this test ensures that the validateUserInput method properly validates user input.
//     */
//    @Test
//    public void testUserInputValidation(){
//        try{
//            //Assert that good input passes validation.
////            assertEquals(true, validateUserInput.validateInput(simulatedInput));
//
////            validateUserInput.validate("Bad Input");
//
//            // if this line is reached, the validation method failed to validate correctly.
////            fail("the validate function accepted bad input!");
//
//        } catch(Exception e){
//            //assert that the message thrown is 'Invalid Input'
//            assertEquals("Invalid Input", e.getMessage());
//        }
//    }
//
//    /**
//     * This test ensures that the user can only input the hallways associated with mount royal.
//     */
//    @Test
//    public void testGetUserInputAcceptsHallwayIds(){
//        //todo need to change this test so that it tests hallway lookups.
////        assertEquals(mruHallways[1], locationData.getHallwayId(simulatedInput));
//    }
//
//    /**
//     * This test ensures that if a user looks up a classroom or hallway, location data also belongs to that hallway/classroom.
//     */
//    @Test
//    public void testLocationDataIsFound(){
//        String[] hallway = locationData.getLocationOfHallway("B");
//        assertEquals(hallway[0], "B");
//        assertEquals(hallway[1], "51.012210");
//        assertEquals(hallway[2], "-114.130732");
//    }
//
//    /**
//     * this test ensures that the location data stored is within mru grounds.
//     */
//    @Test
//    public void testLocationDataIsInMountRoyal(){
//        LatLng latlngValid =new LatLng(51.012210f, -114.130732);
//        LatLng latlngInvalid =new LatLng(50.012210f, -115.130732);
//
//        assertEquals(true, locationData.isInMountRoyal(latlngValid));
//        assertEquals( false, locationData.isInMountRoyal(latlngInvalid));
//    }

}
