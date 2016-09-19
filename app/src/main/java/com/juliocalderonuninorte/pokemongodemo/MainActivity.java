package com.juliocalderonuninorte.pokemongodemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = null;
    private String[] lat = new String[10];
    private String[] lng = new String[10];
    private String[] pos = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetData().execute();
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });


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

            if (response != null){
                try {

                    JSONArray latlng = new JSONArray(response);
                    for (int i=0; i<10;i++){
                        JSONObject c = latlng.getJSONObject(i);
                        lat[i] = c.getString("lt");
                        lng[i] = c.getString("lng");
                        pos[i] = lat[i]+" "+lng[i];
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }else{

            }

            return null;
        }
    }

}
