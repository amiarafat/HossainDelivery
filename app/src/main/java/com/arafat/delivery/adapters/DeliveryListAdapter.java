package com.arafat.delivery.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arafat.delivery.R;
import com.arafat.delivery.models.DeliveryItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DeliveryListAdapter extends RecyclerView.Adapter<DeliveryListAdapter.DeliveryListViewHolder> {

    private Context context;
    private ArrayList<DeliveryItems> deliveryList;

    /**
     * call from Run Sheets Activity class
     *
     * @param context
     * @param deliveryList
     */
    public DeliveryListAdapter(Context context, ArrayList<DeliveryItems> deliveryList) {
        this.context = context;
        this.deliveryList = deliveryList;
    }

    @NonNull
    @Override
    public DeliveryListAdapter.DeliveryListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.each_row_delivey_items, viewGroup, false);
        return new DeliveryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryListAdapter.DeliveryListViewHolder deliveryListViewHolder, int i) {

        DeliveryItems items = deliveryList.get(i);

        deliveryListViewHolder.tvDeliveryInfo.setText(items.getDeliveryDescription());
        deliveryListViewHolder.tvDeliveryAddress.setText("at "+ items.getDeliverylocationAddress());

        Log.d("item::",items.getDeliveryId()+"");

        Picasso.get().load(items.getDeliveryImageUrl()).into(deliveryListViewHolder.ivDeliveryPerson);

    }

    @Override
    public int getItemCount() {
        return deliveryList.size();
    }

    public class DeliveryListViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDeliveryInfo;
        private final TextView tvDeliveryAddress;
        private final CircleImageView ivDeliveryPerson;

        public DeliveryListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeliveryInfo = itemView.findViewById(R.id.tvDeliveryInfo);
            tvDeliveryAddress = itemView.findViewById(R.id.tvDeliveryAddress);
            ivDeliveryPerson = itemView.findViewById(R.id.ivDeliveryImage);
        }
    }
}
