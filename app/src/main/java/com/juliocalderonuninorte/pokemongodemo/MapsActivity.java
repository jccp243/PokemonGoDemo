package com.juliocalderonuninorte.pokemongodemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = null;
    private GoogleMap mMap;
    private MarkerDataSource data;
    private static String[] lat;
    private static String[] lng;
    private static String[] pos;
    private List<Posiciones> posiciones;
    private ArrayList<Integer> timeNames = new ArrayList<Integer>();
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMapAsync(this);
        lat = new String[10];
        lng = new String[10];
        pos = new String[10];
        timeNames.add(R.drawable.caterpie);
        timeNames.add(R.drawable.p1);
        timeNames.add(R.drawable.charizard);
        timeNames.add(R.drawable.geodude);
        timeNames.add(R.drawable.pidgeotto);
        timeNames.add(R.drawable.poliwrath);
        timeNames.add(R.drawable.seel);
        timeNames.add(R.drawable.squirtle);
        timeNames.add(R.drawable.voltorb);
        timeNames.add(R.drawable.wigglytuff);
        new GetData().execute();



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

        //setUpMap();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange (Location location) {
                LatLng loc = new LatLng (location.getLatitude(), location.getLongitude());
                MarkerOptions marker = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()));
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pikachu));
                mMap.addMarker(marker);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16));
            }
        };
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
    }

   protected static String getData(){
        Log.d(TAG,"GET");
        String response = null;
        try {
            URL url = null;
            url = new URL("http://190.144.171.172/function3.php?lat=11.0199414&lng=-74.8487154");
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response=inputLine;
            }
            in.close();

            return response;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private class GetData extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            String response = getData();
            posiciones = new ArrayList<Posiciones>();
            if (response != null){
                try {
                    JSONArray latlng = new JSONArray(response);
                    for (int i=0; i<10;i++){
                        JSONObject c = latlng.getJSONObject(i);
                        lat[i] = c.getString("lt");
                        lng[i] = c.getString("lng");
                        posiciones.add(new Posiciones(lat[i],lng[i]));
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }else{

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i=0; i<posiciones.size(); i++){
                LatLng lat = new LatLng(Double.valueOf(posiciones.get(i).getLat()), Double.valueOf(posiciones.get(i).getLng()));
                MarkerOptions marker = new MarkerOptions().position(lat);
                marker.icon(BitmapDescriptorFactory.fromResource(timeNames.get(i)));
                mMap.addMarker(marker);
            }
        }
    }

}
