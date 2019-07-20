package com.arafat.delivery.models;

public class DeliveryItems {

    private int deliveryId;
    private String deliveryDescription;
    private String deliveryImageUrl;
    private double deliverylocationLat;
    private double deliverylocationLng;
    private String deliverylocationAddress;


    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryDescription() {
        return deliveryDescription;
    }

    public void setDeliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }

    public String getDeliveryImageUrl() {
        return deliveryImageUrl;
    }

    public void setDeliveryImageUrl(String deliveryImageUrl) {
        this.deliveryImageUrl = deliveryImageUrl;
    }

    public double getDeliverylocationLat() {
        return deliverylocationLat;
    }

    public void setDeliverylocationLat(double deliverylocationLat) {
        this.deliverylocationLat = deliverylocationLat;
    }

    public double getDeliverylocationLng() {
        return deliverylocationLng;
    }

    public void setDeliverylocationLng(double deliverylocationLng) {
        this.deliverylocationLng = deliverylocationLng;
    }

    public String getDeliverylocationAddress() {
        return deliverylocationAddress;
    }

    public void setDeliverylocationAddress(String deliverylocationAddress) {
        this.deliverylocationAddress = deliverylocationAddress;
    }
}
