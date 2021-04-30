package com.example.mobile_hw2.ui.bookmark;

/**
 * DTO for bookmark entity.
 */
public class Bookmark {

    private String title;
    private double longitude;
    private double latitude;

    public Bookmark(String title, double longitude, double latitude) {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }
}
