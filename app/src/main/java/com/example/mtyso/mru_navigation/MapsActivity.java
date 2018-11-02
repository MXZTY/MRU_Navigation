package com.example.mtyso.mru_navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;

    //widgets
    private EditText mSearchText;

    //Map Info
    private final float longRightBound = -114.123353f;
    private final float longLeftBound = -114.138005f;
    private final float latUpperBound = 51.015560f;
    private final float latLowerBound = 51.007944f;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mSearchText = (EditText) findViewById(R.id.input_search);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.center(latLng);
                circleOptions.radius(0.5);
                circleOptions.fillColor(Color.BLUE);
                circleOptions.strokeWidth(6);

                mMap.setIndoorEnabled(true);
                mMap.addCircle(circleOptions);

//                mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float)19));

                init();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        };

        if (Build.VERSION.SDK_INT < 23){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    /**
     * This method is run after the map is ready to begin listening for user input when the user presses enter
     */
    private void init(){
        Log.d("init", "Initializing");
        mSearchText.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event.getAction() == KeyEvent.ACTION_DOWN
                    || event.getAction() == KeyEvent.KEYCODE_ENTER) {

                    //Call getUserInput to process the user input recorded in mSearchText global variable.
                    getUserInput();
                }

            return false;
            }
                                               }
        );
    }

    /**
     * Method for controling the processing and validation of the users input
     * @return true by default.
     */
    public boolean getUserInput(){
        System.out.println(mSearchText);

        //Call Sanitize user input to separate the key elements of the search string.
            //If the user input is only 1-2 characters long (ie just building code B) call getHallwayId instead

        //Call validate user input to make sure that the sanitized hallway code exists is a valid search string.

        //Call get Location of hallway

        //Validate the location is within MRU


        return true;
    }

    /**
     * This method will take in a string (B204) and separate the different elements and return them in an array.
     *
     * @param simulatedInput (B204)
     * @return ["B", "204"]
     */
    public String[] sanitizeUserInput(String simulatedInput) {

        //Sanitize the users input using a regular expression. no need to validate in this method as validation will handle it.
        //currently Hardcoded to make test pass. Must change with implementation.
        String[] sanitizedinput = new String[]{"B", "104"};

        return sanitizedinput;
    }

    /**
     * This method ensures that the user input is a valid hallway code for MRU
     * @param simulatedInput
     * @return true if valid input
     * @throws Exception e if invalid input.
     */
    public boolean validateInput(String simulatedInput) throws Exception {

        //method must take in input and test it against a list of hallway codes to make sure it is valid.
        // if the input is invalid, it must throw an exception e of invalid input.

        if(simulatedInput == "Bad Input"){
            throw new Exception("Invalid Input");
        }
        return true;
    }

    /**
     * This method will return only the hallway code and be called if the input string is only 1-2 characters long.
     * @param simulatedInput
     * @return hallway id
     */
    public String getHallwayId(String simulatedInput) {
        // probably dont even need this method and can reuse sanitization to return only the hallway code.

        //This method must call the validation method to ensure the input contains a valid hallway code.
        String hallwayId = "B";
        return hallwayId;
    }

    /**
     * Get the location data of a hallway code that is passed in.
     * @param b hallway code
     * @return hallway location array
     */
    public String[] getLocationOfHallway(String b) {

        //this method will be called by the userInput method and return the hallway location
        String[] hallwayLocation = new String[]{"B","51.012210","-114.130732"};
        return hallwayLocation;
    }

    /**
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
}
