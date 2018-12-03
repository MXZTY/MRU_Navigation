package com.example.mtyso.mru_navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private boolean focus = true;

    //need an array for storing the users history so it can be accessed for the userHistoryTab
    public static ArrayList<String> userHistory = new ArrayList<>();
    public static ArrayList<String> userFavourites = new ArrayList<>();

    //widgets
    private EditText mSearchText;
    private BottomNavigationView mMainNav;
    private boolean pinSet = false;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mSearchText = findViewById(R.id.input_search);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMainNav = findViewById(R.id.main_nav);

        //Add a listener to the navigation menu for setting the button actions.
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.explore:
                        startActivity(new Intent(MapsActivity.this, PopupScreen_Explore.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(MapsActivity.this, PopupScreen_History.class));
                        return true;
                    case R.id.favourites:
                        startActivity(new Intent(MapsActivity.this, PopupScreen_Favourites.class));
                        return true;
                    default: return false;
                }


            }
        });

        goToMyLocation();
    }

    /**
     * This method requests the users location permissions if they are not already set.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
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
//        mMap.setIndoorEnabled(true);
//        mMap.setBuildingsEnabled(true);
//        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMaxZoomPreference(18f);
        mMap.setMinZoomPreference(15f);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(51.012374, -114.131309)).zoom(16.5f).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


//        try {
//            // Customise the styling of the base map using a JSON object defined
//            // in a raw resource file.
//            boolean success = googleMap.setMapStyle(
//                    MapStyleOptions.loadRawResourceStyle(
//                            this, R.raw.style_map));
//            if (!success) {
//                Log.e("parseError", "Style parsing failed.");
//            }
//        } catch (Resources.NotFoundException e) {
//            Log.e("no style", "Can't find style. Error: ", e);
//        }
    }

    /**
     * This method is run after the map is ready to begin listening for user input when the user presses enter
     */
    private void init(){
        mSearchText.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                LocationAccessLayer locations = new LocationAccessLayer();
                //when the user inputs a string and presses enter
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event.getAction() == KeyEvent.ACTION_DOWN
                    || event.getAction() == KeyEvent.KEYCODE_ENTER) {

                    //Call getUserInput to process the user input recorded in mSearchText global variable.
                    try {
                        String searchText = mSearchText.getText().toString();
                        //Add users search text to the search history.
                        userHistory.add(mSearchText.getText().toString());
                        if(locations.validateUserInput(searchText)){
                            // if it is a valid location, unfocus from the users locaiton and add a new marker for the returned location instance.
                            focus = false;
//                                mMap.clear();
//                                locationListener.onLocationChanged(location);
                            mMap.addMarker(new MarkerOptions().position(locations.getLocation(searchText).getLocation()).title(getString(R.string.classroom)));
//                            LatLng moveTo = new LatLng(locations.getLocation(searchText).getLatitude(), locations.getLocation(searchText).getLongitude());
//                            mMap.moveCamera(CameraUpdateFactory.newLatLng(moveTo));
                        }
                    } catch (Exception e) {
                        //TODO handle invalid input here
                        System.out.println(e.getMessage());
                    }
                }
                return false;
            }
        });
    }

    /**
     * This method requests the users location through the implementation of a locationListener sub class
     * It will retrieve the users location and display it with a red circle on the Map
     */
    public void goToMyLocation(){
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.center(latLng);
                circleOptions.radius(4);
                circleOptions.fillColor(Color.BLUE);
                circleOptions.strokeColor(Color.BLUE);
                circleOptions.strokeWidth(7);
                mMap.addCircle(circleOptions);

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

}
