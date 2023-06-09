package dev.sareth.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dev.sareth.contact.models.BoxLocation;
import dev.sareth.contact.models.Landscape;


public class MapLocation extends AppCompatActivity implements OnMapReadyCallback{

    private MapView mapView;
    private Landscape landscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);

        // Getting location
        Intent intent = this.getIntent();
        this.landscape = (Landscape) intent.getSerializableExtra("location");

        // Getting components

        this.mapView = this.findViewById(R.id.mapView);
        this.mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        // Getting location
        BoxLocation boxLocation = this.landscape.getContactLocation();

        // Create an LatLng object with our data
        LatLng location = new LatLng(
                boxLocation.getLatitude(), boxLocation.getLongitude()
        );

        // Add marker at the location
        googleMap.addMarker(new MarkerOptions()
                .position(location).title(this.landscape.getName()));

        // Move the camera to the location and zoom in
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                location, 15F
        ));

        this.mapView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.mapView.onLowMemory();
    }
}