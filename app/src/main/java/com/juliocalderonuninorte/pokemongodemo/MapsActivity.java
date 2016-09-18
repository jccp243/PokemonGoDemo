package com.juliocalderonuninorte.pokemongodemo;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerDataSource data;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMapAsync(this);

        // LO QUE APARECE EN COMENTARIO ES CUANDO YA SE TIENE LA BASE DE DATOS
        /*data = new MarkerDataSource(context);
        try {
            data.open();
        } catch (Exception e){

        }

        data.close();
*/
        /*List<MyMarkerObj> m = data.getMyMarkers();

            //ESTE PARA RECORRE LA BASE DE DATOS CON LAS SUPUESTAS POSICIONES QUE ESTEN EN LA BASE
            DE DATOS Y LOS MARCA EN EL MAPA CON LA INFORMACION ASOCIADA (SNIPPET)

        for (int i=0; i<m.size(); i++){
            String[] slatlng = m.get(i).getPosition().split(" ");
            LatLng lat = new LatLng(Double.valueOf(slatlng[0]), Double.valueOf(slatlng[1]));
            mMap.addMarker(new MarkerOptions().title(m.get(i).getTitle()).snippet(m.get(i).getSnippet()).position(lat));
        }*/

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

}
