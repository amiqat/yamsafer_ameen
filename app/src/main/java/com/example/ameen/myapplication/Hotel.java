package com.example.ameen.myapplication;

/**
 * Created by Ameen on 23-Dec-2015.
 */
public class Hotel {

    private int hotel_id;
    private String hotel_name;
    private String coverImage;
    private boolean is_cardless;
    private boolean pre_paid;
    private String distance_from_city_center;
    private double avgRate;
    private int stars;


    public Hotel(int hotel_id, String hotel_name) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
    }

    public Hotel(String hotel_name, int hotel_id, String coverImage, boolean is_cardless, boolean pre_paid, String distance_from_city_center, int avgRate) {
        this.hotel_name = hotel_name;
        this.hotel_id = hotel_id;
        this.coverImage = coverImage;
        this.is_cardless = is_cardless;
        this.pre_paid = pre_paid;
        this.distance_from_city_center = distance_from_city_center;
        this.avgRate = avgRate;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean is_cardless() {
        return is_cardless;
    }

    public void setIs_cardless(boolean is_cardless) {
        this.is_cardless = is_cardless;
    }

    public boolean isPre_paid() {
        return pre_paid;
    }

    public void setPre_paid(boolean pre_paid) {
        this.pre_paid = pre_paid;
    }

    public String getDistance_from_city_center() {
        return distance_from_city_center;
    }

    public void setDistance_from_city_center(String distance_from_city_center) {
        this.distance_from_city_center = distance_from_city_center;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", is_cardless=" + is_cardless +
                ", pre_paid=" + pre_paid +
                ", distance_from_city_center='" + distance_from_city_center + '\'' +
                ", avgRate=" + avgRate +
                ", stars=" + stars +
                '}';
    }
}
