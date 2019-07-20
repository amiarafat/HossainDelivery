package com.arafat.delivery;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arafat.delivery.adapters.DeliveryListAdapter;
import com.arafat.delivery.apis.APIConstants;
import com.arafat.delivery.helper.ClickListener;
import com.arafat.delivery.helper.RecyclerTouchListener;
import com.arafat.delivery.models.DeliveryItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private int offset = 0;
    private static int limit = 20;

    //RecyclerView for list of Deliveries
    RecyclerView rvDeliveries;

    //Progress Bar
    ProgressBar pbLoading;


    //
    private DeliveryListAdapter deliveryAdapter;
    private ArrayList<DeliveryItems> deliveryArrayList = new ArrayList<>();
    private int mStatusCode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        getDeliveryLists(offset,limit);

        rvDeliveries.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, rvDeliveries, new ClickListener() {
            @Override
            public void onClick(View view, int position) {


                Intent in = new Intent(MainActivity.this,DeliveryDetailsActivity.class);
                in.putExtra("-ImageUrl",deliveryArrayList.get(position).getDeliveryImageUrl());
                in.putExtra("-deliveryDetails",deliveryArrayList.get(position).getDeliveryDescription());
                in.putExtra("-deliveryAddress",deliveryArrayList.get(position).getDeliverylocationAddress());
                in.putExtra("-deliveryLat",deliveryArrayList.get(position).getDeliverylocationLat());
                in.putExtra("-deliveryLng",deliveryArrayList.get(position).getDeliverylocationLng());
                startActivity(in);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        rvDeliveries.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {

                    getDeliveryLists(offset,limit);

                }
            }
        });

    }

    private void initView() {
        rvDeliveries = findViewById(R.id.rvDeliveries);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvDeliveries.setLayoutManager(layoutManager);

        deliveryAdapter = new DeliveryListAdapter(this, deliveryArrayList);
        rvDeliveries.setAdapter(deliveryAdapter);

        pbLoading = findViewById(R.id.pbLoading);
    }


    private void getDeliveryLists(int listOffset, int listLimit){
        pbLoading.setVisibility(View.VISIBLE);

        StringRequest request =  new StringRequest(Request.Method.GET, APIConstants.Deliveries.API_DELIVERY_LIST + "?offset=" + listOffset + "&limit=" + listLimit, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pbLoading.setVisibility(View.GONE);

                Log.d("status:",mStatusCode+"");
                if(mStatusCode == 200){
                    Log.d("res::",response);
                    parseDeliveryList(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pbLoading.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };

         RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
         requestQueue.add(request);

        // HossainDelivery.getInstance().addToRequestQueue(request, TAG);
    }

    private void parseDeliveryList(String response) {

        DeliveryItems items;

        try {
            JSONArray deliveryArray = new JSONArray(response);

            for (int i = 0;i<deliveryArray.length();i++){

                JSONObject obj =  deliveryArray.getJSONObject(i);
                items =  new DeliveryItems();

                items.setDeliveryId(obj.getInt("id"));
                items.setDeliveryDescription(obj.getString("description"));
                items.setDeliveryImageUrl(obj.getString("imageUrl"));
                String location = obj.getString("location");
                JSONObject jlocation = new JSONObject(location);
                items.setDeliverylocationLat(jlocation.getDouble("lat"));
                items.setDeliverylocationLng(jlocation.getDouble("lng"));
                items.setDeliverylocationAddress(jlocation.getString("address"));

                deliveryArrayList.add(items);
            }

            offset = deliveryArrayList.size();
            Log.d("offset:",offset+"");

            deliveryAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
