package com.arafat.delivery;

import android.os.Bundle;

import com.arafat.delivery.constants.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.MenuItem;
import android.widget.TextView;

public class DeliveryDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    CircleImageView ivDeliveryRImage;
    TextView tvDAddress, tvDDetails;

    String imUrl ;
    String DeliveryDetails ;
    String DeliveryAddress ;
    Double deliveryLat;
    Double deliveryLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        initView();

        getDetailInfo();
    }

    private void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.delivery_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //view initialize
        ivDeliveryRImage = findViewById(R.id.ivDeliveryRImage);
        tvDDetails = findViewById(R.id.tvDeliveryDetails);
        tvDAddress = findViewById(R.id.tvDeliveryRAddress);
    }

    private void getDetailInfo() {

        if(getIntent()!=null){

            imUrl = getIntent().getStringExtra(Constants.DELIVERYIMAGEURL);
            DeliveryDetails = getIntent().getStringExtra(Constants.DELIVERYDETAILS);
            DeliveryAddress = getIntent().getStringExtra(Constants.DELIVERYADDRESS);
            deliveryLat = getIntent().getDoubleExtra(Constants.DELIVERYLAT,0.0);
            deliveryLng = getIntent().getDoubleExtra(Constants.DELIVERYLNG,0.0);

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng DeliveryLocation = new LatLng(deliveryLat,deliveryLng);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(DeliveryLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DeliveryLocation, 16f), 1000, null);
        mMap.addMarker(new MarkerOptions().position(DeliveryLocation).title("Delivery Location"));

        tvDDetails.setText(DeliveryDetails);
        tvDAddress.setText("at "+DeliveryAddress);
        Picasso.get().load(imUrl).into(ivDeliveryRImage);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
}
