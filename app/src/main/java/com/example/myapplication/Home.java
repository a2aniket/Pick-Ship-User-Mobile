package com.example.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;


import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;
import java.util.List;

import static com.example.myapplication.R.color.*;

public class Home extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    String product_S;
    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    private TextView tvPlaceDetails;
    private TextView fabPickPlace;
    private TextView dlocation;
    String checkadd;
    String loc;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final EditText inst = (EditText) findViewById(R.id.inst);
        final EditText simpleEditText = (EditText) findViewById(R.id.product);
        final ImageView empty1 = (ImageView) findViewById(R.id.empty1);
        final ImageView ok = (ImageView) findViewById(R.id.ok);
        final ImageView empty2 = (ImageView) findViewById(R.id.empty2);
        final ImageView ok2 = (ImageView) findViewById(R.id.ok2);
        final EditText location =(EditText) findViewById(R.id.location);
        final EditText dLocations =(EditText) findViewById(R.id.dLocations);
        final ImageView empty3 = (ImageView) findViewById(R.id.empty3);
        final ImageView ok3 = (ImageView) findViewById(R.id.ok5);
        final Button confirm = (Button) findViewById(R.id.confirm);
        //final Button b=(Button) findViewById(R.id.b);
        final Handler handler = new Handler();

        confirm.setBackgroundColor(Color.GRAY);


         Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                String sUsername = location.getText().toString();
                String product = simpleEditText.getText().toString();
                if (sUsername.matches(""))
                {
                    empty1.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.GONE);
                    dLocations.setEnabled(false);
                    simpleEditText.setEnabled(false);
                    inst.setEnabled(false);
                    confirm.setEnabled(false);
                    confirm.setBackgroundColor(Color.GRAY);
                }
                else
                {
                    ok.setVisibility(View.VISIBLE);
                    empty1.setVisibility(View.GONE);
                    dLocations.setEnabled(true);
                    //confirm.setEnabled(false);
                    confirm.setBackgroundColor(Color.GRAY);

                }

                String dlocation1 = dLocations.getText().toString();
                if (dlocation1.matches(""))
                {
                    empty2.setVisibility(View.VISIBLE);
                    ok2.setVisibility(View.GONE);
                    simpleEditText.setEnabled(false);
                    inst.setEnabled(false);
                    confirm.setEnabled(false);
                    confirm.setBackgroundColor(Color.GRAY);

                }
                else
                {
                    ok2.setVisibility(View.VISIBLE);
                    empty2.setVisibility(View.GONE);
                    simpleEditText.setEnabled(true);
                    //confirm.setEnabled(false);
                    confirm.setBackgroundColor(Color.GRAY);

                }

                if (product.matches(""))
                {
                    empty3.setVisibility(View.VISIBLE);
                    ok3.setVisibility(View.GONE);
                    inst.setEnabled(false);

                }
                else
                {
                    confirm.setEnabled(true);
                    if (dlocation1.matches(""))
                    {
                        confirm.setEnabled(false);
                        confirm.setBackgroundColor(Color.GRAY);

                    }
                    else {

                        ok3.setVisibility(View.VISIBLE);
                        empty3.setVisibility(View.GONE);
                        inst.setEnabled(true);
                        confirm.setBackgroundColor(Color.rgb(164,198,57));
                        confirm.setEnabled(true);

                    }
                }

                if(dlocation1.matches(""))
                {
                    confirm.setBackgroundColor(Color.GRAY);
                    confirm.setEnabled(false);

                }





                handler.postDelayed(this, 2);
            }
        };
        handler.post(runnableCode);


        simpleEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
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


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ConformOrder.class);

               // intent.putExtra("a", (CharSequence) location);

                startActivity(intent);
            }
        });

       /* location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Plocation.class);
                startActivity(intent);*/


         /*   }
        });
        String passedArg = getIntent().getExtras().getString("address");
        location.setText(passedArg);*/


        initViews();

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


        fabPickPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loc="plocation";
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Home.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        dlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loc="dlocation";
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Home.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initViews() {
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar)*/;
        fabPickPlace = (TextView) findViewById(R.id.location);
        dlocation =(TextView) findViewById(R.id.dLocations);
        tvPlaceDetails = (TextView) findViewById(R.id.location);
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
                if(loc=="plocation") {

                    stBuilder.append(address);
                    tvPlaceDetails.setText(stBuilder.toString());
                    if(address.contains("Ambegaon")) {
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Currently not serving in your area");
                        builder.setMessage("The service will start as posbile as")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        tvPlaceDetails.setText("");
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        tvPlaceDetails.setText("");
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertdialog = builder.create();
                        alertdialog.show();
                    }

                }

                if(loc=="dlocation")
                {
                    stBuilder.append(address);
                    checkadd=address;
                    dlocation.setText(stBuilder.toString());

                    if(address.contains("Ambegaon")) {
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Currently not serving in your area");
                        builder.setMessage("The service will start as posbile as")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dlocation.setText("");
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dlocation.setText("");
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertdialog = builder.create();
                        alertdialog.show();
                    }
                }
            }
            }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
