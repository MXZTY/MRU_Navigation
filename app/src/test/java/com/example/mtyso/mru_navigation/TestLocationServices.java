package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLocationServices {

    //classes involved with testing this functional requirement.
    private LocationAccessLayer locationData;

    //resources to use for test
    private String simulatedInput;
    private String[] pLots;

    @Before
    public void setUp() {
        this.locationData = new LocationAccessLayer();
    }

    /**
     * This will test that the hash map is built on application startup.
     */
    @Test
    public void testHashMapIsBuilt(){
        Hallway hall = new Hallway("EA", new LatLng(51.012276,-114.129124), "hall");
        PointOfInterest poi = new PointOfInterest("Bookstore", new LatLng(51.012613, -114.131476), "poi");
        ParkingLot lot = new ParkingLot("LotA", new LatLng(51.008836,-114.133970), "pLot", true, false);

        try {
            DataHandler1.getInstance().add(hall.getName(), hall);
            DataHandler1.getInstance().add(poi.getName(), poi);
            DataHandler1.getInstance().add(lot.getName(), lot);
            assertTrue(locationData.getSize()>0);

            //Cannot guarantee the order of tests being completed so testing the size of the array can result in different sizes if another test has executed before this test.
            //Need to implement a different test class to assert hash map sizes.
//            assertEquals(locationData.getSize(), 3);
//            assertTrue(locationData.getSize()<4);

        } catch(Exception e){
            fail(e.getMessage());
        }

    }


    /**
     * This test will ensure that all correct hallway codes can be looked up by the user.
      */
    @Test
    public void testCanLookUpHallway(){
        Hallway hall = new Hallway("EA", new LatLng(51.012276,-114.129124), "hall");
        try {
            DataHandler1.getInstance().add(hall.getName(), hall);
            assertNotNull(locationData.getLocation(hall.getName()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * This test ensures that all the correct POIs in MRU can be looked up
     */
    @Test
    public void testCanLookupPOI(){
        PointOfInterest poi = new PointOfInterest("Bookstore", new LatLng(51.012613, -114.131476), "poi");
        try {
            DataHandler1.getInstance().add(poi.getName(), poi);
            assertNotNull(locationData.getLocation(poi.getName()));
        } catch (Exception e) {
                fail(e.getMessage());
        }
    }

    /**
     * This test ensures that all the correct parking lot information can be looked up.
     */
    @Test
    public void testCanLookupParkingLots(){
        ParkingLot lot = new ParkingLot("LotA", new LatLng(51.008836,-114.133970), "pLot", true, false);
        try {
            DataHandler1.getInstance().add(lot.getName(), lot);
            assertNotNull(locationData.getLocation(lot.getName()) );
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * This test will ensure that the user input, and the values within the Json data is formatted correctly for adding and searching through the hash map
     * the formatting should get rid of all
     */
    @Test
    public void testTextFormattingForHashMap(){
        assertEquals("teststringisatest", DataHandler1.getInstance().formatText("Test_String is a TEST _ "));
    }


    /**
     * This test ensures that if a user looks up a classroom or hallway, location data also belongs to that hallway/classroom.
     */
    @Test
    public void testLocationDataCanBeFound(){
        try {
            Hallway hall = new Hallway("B", new LatLng(51.012210,-114.130732), "hall");
            DataHandler1.getInstance().add("B", hall);
            LocationInstance hallway = locationData.getLocation("B");
            assertNotNull("The object returned should not be null", hallway);
            assertEquals(hallway.getName(), "B");
            assertEquals("" + hallway.getLocation().latitude, "51.01221");
            assertEquals("" + hallway.getLocation().longitude, "-114.130732");

        }catch(Exception e){
            fail(e.getMessage());
        }

    }

    /**
     * this test ensures that the location data stored is within mru grounds.
     */
    @Test
    public void testLocationDataIsInMountRoyal(){
        LatLng valid =new LatLng(51.012210f, -114.130732);
        LatLng invalid =new LatLng(50.012210f, -115.130732);

        assertEquals(true, locationData.isInMountRoyal(valid));
        assertEquals( false, locationData.isInMountRoyal(invalid));
    }


    //ToDo implement test to retrieve all Hallways so they may be displayed in the bottom navigation menu.
    @Test
    public void itCanGetAllHallways(){

    }

    //ToDo implement test to retrieve all Points Of Interest so they may be displayed in the bottom navigation menu.
    @Test
    public void itCanGetAllPOIs(){
//        assertTrue(pois.length == locationData.getAllPois().length);
    }

    //ToDo implement test to retrieve all parking lots so they may be displayed in the bottom navigation menu.
    @Test
    public void itCanGetAllParkingLots(){
//        assertTrue(pLots.length == locationData.getAllParkingLots().length);
    }
}
