package com.example.mtyso.mru_navigation;

import com.google.android.gms.maps.model.LatLng;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import static java.util.regex.Pattern.matches;
import static org.junit.Assert.*;

public class TestSearchBarFunctionality {

    private MapsActivity activity;
    private String simulatedInput;
    private String[] mruHallways;



    @Before
    public void setUp() throws Exception {
        this.activity = new MapsActivity();
        this.simulatedInput = "B104";
        this.mruHallways = new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","N","O","Q","R","S","T","U","V","W","X","Y","Z","EA","EB","EC","ED","EL"};
    }

    /**
     * this test ensures that the input sanitization method is working properly.
     */
    @Test
    public void testUserInputSanitization(){
        assertEquals("B", activity.sanitizeUserInput(simulatedInput)[0]);
        assertEquals("104", activity.sanitizeUserInput(simulatedInput)[1]);
    }

    /**
     * this test ensures that the validateUserInput method properly validates user input.
     */
    @Test
    public void testUserInputValidation(){
        try{
            //Assert that good input passes validation.
            assertEquals(true, activity.validateInput(simulatedInput));
            activity.validateInput("Bad Input");

            // if this line is reached, the validation method failed to validate correctly.
            fail("the validate function accepted bad input!");

        } catch(Exception e){
            //assert that the message thrown is 'Invalid Input'
            assertEquals("Invalid Input", e.getMessage());
        }
    }

    /**
     * This test ensures that the user can only input the hallways associated with mount royal.
     */
    @Test
    public void testGetUserInputAcceptsHallwayIds(){
        assertEquals(mruHallways[1], activity.getHallwayId(simulatedInput));
    }

    /**
     * This test ensures that if a user looks up a classroom or hallway, location data also belongs to that hallway/classroom.
     */
    @Test
    public void testLocationDataIsFound(){
        String[] hallway = activity.getLocationOfHallway("B");
        assertEquals(hallway[0], "B");
        assertEquals(hallway[1], "51.012210");
        assertEquals(hallway[2], "-114.130732");
    }

    /**
     * this test ensures that the location data stored is within mru grounds.
     */
    @Test
    public void testLocationDataIsInMountRoyal(){
        LatLng latlngValid =new LatLng(51.012210f, -114.130732);
        LatLng latlngInvalid =new LatLng(50.012210f, -115.130732);

        assertEquals(true, activity.isInMountRoyal(latlngValid));
        assertEquals( false, activity.isInMountRoyal(latlngInvalid));
    }

}
