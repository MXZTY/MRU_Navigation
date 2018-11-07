//package com.example.mtyso.mru_navigation;
//
//import android.content.res.Resources;
//import android.support.v4.graphics.drawable.IconCompat;
//
//import com.google.android.gms.maps.model.LatLng;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.io.File;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class TestLocationServices {
//
//    //classes involved with testing this functional requirement.
//    private MapsActivity activity;
//    private LocationServices locationData;
//    private String[] pois;
//
//    //resources to use for test
//    private String simulatedInput;
//    private String[] mruHallways;
//    private String[] pLots;
//
//    @Before
//    public void setUp() throws Exception {
//
//        this.locationData = new LocationServices();
//        this.activity = new MapsActivity();
//        this.mruHallways = new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","O","Q","R","S","T","U","V","W","X","Y","Z","EA","EB","EC","ED","EL"};
//        this.pLots = new String[]{
//                "Lot 1",
//                "Lot 2",
//                "Lot 3",
//                "Lot 4",
//                "Lot 5",
//                "Lot 6",
//                "Lot 6a",
//                "Lot 7",
//                "Lot 8",
//                "Lot 9",
//                "East Gate Parkade",
//        };
//        this.pois = new String[]{
//                "Triple Gymnasium",
//                "Aquatic Center",
//                "Fitness Center",
//                "Recreation Customer Service",
//                "Running Track",
//                "Wyckham House",
//                "Digital Print Center",
//                "Main Street",
//                "Registrars Office",
//                "Admissions and Recruitment",
//                "Kerby Hall",
//                "Event And Theater Services",
//                "Ross Glen Hall",
//                "Atelier",
//                "Box Office",
//                "Bella Concert Hall",
//                "Conservatory Services",
//                "Parking Office",
//                "Bookstore",
//                "Student Digital Print Center",
//                "Bisset School Of Business",
//                "Faculty Of Arts",
//                "Recreation Center",
//                "East Entrance",
//                "Roderick Mah Center for Continuous Learning",
//                "Taylor Center for the Performing Arts",
//                "Riddel Library and Learning Center",
//                "West Entrance",
//                "Wellness Center",
//                "Child Care Center",
//                "Leacock and Wright Theaters",
//                "The Table",
//                "Security Office",
//                "TransAlta Pavilion",
//                "Student Learning Services",
//                "Lincoln Park Room",
//                "Department of Education",
//                "Continuing Education Customer Service"
//        };
//
//    }
//
//    @Test
//    public void itCanGetAllHallways(){
////        String[] halls = {
////                "{name:A,lat:51.011502,lng:-114.130802,id:hall}",
////                "{name:B,lat:51.012434,lng:-114.130541,id:hall}",
////                "{name:C,lat:51.0121996,lng:-114.1313158,id:hall}",
////                "{name:D,lat:51.0123245,lng:-114.1316719,id:hall}",
////                "{name:E,lat:51.0119611,lng:-114.1320615,id:hall}",
////                "{name:F,lat:51.0123726,lng:-114.1321011,id:hall}",
////                "{name:G,lat:51.0129634,lng:-114.1315663,id:hall}",
////                "{name:H,lat:51.0125063,lng:-114.1319602,id:hall}",
////                "{name:I,lat:51.0121855,lng:-114.1327585,id:hall}",
////                "{name:J,lat:51.0130598,lng:-114.1332396,id:hall}",
////                "{name:K,lat:51.0132223,lng:-114.1329748,id:hall}",
////                "{name:M,lat:51.0129179,lng:-114.1322207,id:hall}",
////                "{name:N,lat:51.0132630,lng:-114.1322040,id:hall}",
////                "{name:O,lat:51.0134167,lng:-114.1318496,id:hall}",
////                "{name:Q,lat:51.0122429,lng:-114.1314007,id:hall}",
////                "{name:R,lat:51.0129774,lng:-114.1321121,id:hall}",
////                "{name:S,lat:51.0124112,lng:-114.1331019,id:hall}",
////                "{name:T,lat:51.0135849,lng:-114.1316468,id:hall}",
////                "{name:U,lat:51.0127983,lng:-114.1327216,id:hall}",
////                "{name:V,lat:51.0132010,lng:-114.1320578,id:hall}",
////                "{name:W,lat:51.0128162,lng:-114.1331977,id:hall}",
////                "{name:X,lat:51.0133800,lng:-114.1325912,id:hall}",
////                "{name:Y,lat:51.0132307,lng:-114.1330673,id:hall}",
////                "{name:Z,lat:51.0137926,lng:-114.1326311,id:hall}",
////                "{name:EB,lat:51.010972,lng:-114.131208,id:hall}",
////                "{name:EA,lat:51.012276,lng:-114.129124,id:hall}",
////                "{name:EC,lat:51.011072,lng:-114.129473,id:hall}",
////                "{name:ED,lat:51.0114674,lng:-114.1285921,id:hall}",
////                "{name:EL,lat:51.0121927,lng:-114.1279168,id:hall}"
////        };
//
////        try {
////            JSONObject obj = new JSONObject("{name:A,lat:51.011502,lng:-114.130802,id:hall}");
////            System.out.println(obj);
////        } catch (Exception e){
////            System.out.println(e.getMessage());
////        }
////        locationData.buildTable(halls);
//        ArrayList<LocationInstance> result = locationData.getAllHalls();
////        assertTrue("The returned number of hallways should be greater than 1", result.size()>1);
////        assertEquals("Not all hallways have been returned.",halls.length, result.size());
//
//    }
//
//    @Test
//    public void itCanGetAllPOIs(){
////        assertTrue(pois.length == locationData.getAllPois().length);
//    }
//
//    @Test
//    public void itCanGetAllParkingLots(){
////        assertTrue(pLots.length == locationData.getAllParkingLots().length);
//    }
//
//    /**
//     * This will test that the hash map is built on application startup.
//     */
//    @Test
//    public void testHashMapIsBuilt(){
//        assertTrue(locationData.getSize()>0);
//        assertTrue(locationData.getSize()>50);
//    }
//
//
//    /**
//     * This test will ensure that all correct hallway codes can be looked up by the user.
//      */
//    @Test
//    public void testCanLookUpHallway(){
//        for (String hall: mruHallways){
//            try {
//                assertNotNull(locationData.getLocation(hall) );
//            } catch (Exception e) {
//                fail(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * This test ensures that all the correct POIs in MRU can be looked up
//     */
//    @Test
//    public void testCanLookupPOI(){
//        for (String poi: pois){
//            try {
//                assertNotNull(locationData.getLocation(poi.toLowerCase()) );
//            } catch (Exception e) {
//                fail(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * This test ensures that all the correct parking lot information can be looked up.
//     */
//    @Test
//    public void testCanLookupParkingLots(){
//        for (String pLot: pLots){
//            try {
//                assertNotNull(locationData.getLocation(pLot.toLowerCase()) );
//            } catch (Exception e) {
//                fail(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * This test will ensure that the user input, and the Json data is formatted correctly for adding and searching through the hash map
//     * the formatting should get rid of all
//     */
//    @Test
//    public void testTextFormattingForHashMap(){
//        assertEquals("teststringisatest", locationData.formatText("Test_String is a TEST _ "));
//    }
////    /**
////     * todo need to change this test into 2 tests. one for hallway validation and one for classroom validation.
////     * this test ensures that the validateUserInput method properly validates user input.
////     */
////    @Test
////    public void testUserInputValidation(){
////        try{
////            //Assert that good input passes validation.
//////            assertEquals(true, validateUserInput.validateInput(simulatedInput));
////
//////            validateUserInput.validate("Bad Input");
////
////            // if this line is reached, the validation method failed to validate correctly.
//////            fail("the validate function accepted bad input!");
////
////        } catch(Exception e){
////            //assert that the message thrown is 'Invalid Input'
////            assertEquals("Invalid Input", e.getMessage());
////        }
////    }
////
////    /**
////     * This test ensures that the user can only input the hallways associated with mount royal.
////     */
////    @Test
////    public void testGetUserInputAcceptsHallwayIds(){
////        //todo need to change this test so that it tests hallway lookups.
//////        assertEquals(mruHallways[1], locationData.getHallwayId(simulatedInput));
////    }
////
////    /**
////     * This test ensures that if a user looks up a classroom or hallway, location data also belongs to that hallway/classroom.
////     */
////    @Test
////    public void testLocationDataIsFound(){
////        String[] hallway = locationData.getLocationOfHallway("B");
////        assertEquals(hallway[0], "B");
////        assertEquals(hallway[1], "51.012210");
////        assertEquals(hallway[2], "-114.130732");
////    }
////
////    /**
////     * this test ensures that the location data stored is within mru grounds.
////     */
////    @Test
////    public void testLocationDataIsInMountRoyal(){
////        LatLng latlngValid =new LatLng(51.012210f, -114.130732);
////        LatLng latlngInvalid =new LatLng(50.012210f, -115.130732);
////
////        assertEquals(true, locationData.isInMountRoyal(latlngValid));
////        assertEquals( false, locationData.isInMountRoyal(latlngInvalid));
////    }
//
//}
