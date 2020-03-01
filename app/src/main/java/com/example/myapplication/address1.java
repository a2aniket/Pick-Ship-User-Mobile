package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

public class address1 extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    String product_S;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    private TextView location;
    private TextView dlocation;
    private CardView first;
    private CardView second;

    String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address1);
        first = (CardView) findViewById(R.id.first);
        second = (CardView) findViewById(R.id.second);
        final TextView t=(TextView)findViewById(R.id.line);
        final TextView t1=(TextView)findViewById(R.id.line1);
        final TextView location1=(TextView)findViewById(R.id.location);
        final TextView dlocation1=(TextView)findViewById(R.id.dLocations);
        final TextView simpleEditText=(TextView)findViewById(R.id.dLocations);
        final CardView th=(CardView)findViewById(R.id.th);
        t1.setEnabled(false);

       

        t.setEnabled(false);
        th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(address1.this);
                builder.setTitle("Select Package Contents");
                // add a list
                String[] animals = {"Documents | Books", "Clothes | Accessories", "Food | Flowers", "Home Items", "Sports & Other Equipments"};
                builder.setItems(animals, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0: product_S="Documents | Books";   simpleEditText.setText(product_S, TextView.BufferType.EDITABLE); break;
                            case 1: product_S="Clothes | Accessories";   simpleEditText.setText(product_S, TextView.BufferType.EDITABLE); break;
                            case 2: product_S="Food | Flowers";   simpleEditText.setText(product_S, TextView.BufferType.EDITABLE); break;
                            case 3: product_S="Home Items";   simpleEditText.setText(product_S, TextView.BufferType.EDITABLE);break;
                            case 4: product_S="Sports & Other Equipments";   simpleEditText.setText(product_S, TextView.BufferType.EDITABLE);break;
                        }
                    }
                });

                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        initViews();

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loc = "plocation";
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(address1.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loc = "dlocation";
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(address1.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initViews() {
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar)*/
        ;
        location = (TextView) findViewById(R.id.location);
        dlocation = (TextView) findViewById(R.id.dLocations);
        //tvPlaceDetails = (TextView) findViewById(R.id.location);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

   /* public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // Snackbar.make(fabPickPlace, connectionResult.getErrorMessage() + "", Snackbar.LENGTH_LONG).show();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stBuilder = new StringBuilder();
                //String placename = String.format("%s", place.getName());
                //String latitude = String.valueOf(place.getLatLng().latitude);
                //String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                //stBuilder.append("Name: ");
                //stBuilder.append(placename);
                ///stBuilder.append("\n");
                //stBuilder.append("Latitude: ");
                // stBuilder.append(latitude);
                //stBuilder.append("\n");
                //stBuilder.append("Logitude: ");
                //stBuilder.append(longitude);
                //stBuilder.append("\n");
                //stBuilder.append("Address: ");
                if (loc == "plocation") {
                    stBuilder.append(address);
                    location.setText(stBuilder.toString());
                }

                if (loc == "dlocation") {
                    stBuilder.append(address);
                    dlocation.setText(stBuilder.toString());
                }
            }
        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
