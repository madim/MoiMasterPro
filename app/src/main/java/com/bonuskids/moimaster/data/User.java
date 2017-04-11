package com.bonuskids.moimaster.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by madi on 4/8/17.
 */

public class User implements Parcelable {
    private String username;
    private String email;
    private String organization;
    private String role;

    private List<Service> services;
    private List<Review> reviews;

    public User() {

    }

    public User(String email, String username) {
        this.email = email;
        this.username = username;

        services = new ArrayList<>();
        services.add(new Service("Women's Cut", "$125 for 75 minutes"));
        services.add(new Service("Men's Cut", "$80 for 60 minutes"));
        services.add(new Service("Bang Cut", "$25 for 15 minutes"));
        services.add(new Service("Color Camo", "$55 for 30 minutes"));
        services.add(new Service("Brow Shaping", "$45 for 30 minutes"));
        services.add(new Service("Brow Tint", "$15 for 15 minutes"));

        reviews = new ArrayList<>();
        reviews.add(new Review(5, new Service("Men's Cut"), "", "- Chris P. | 01.14.2017"));
        reviews.add(new Review(5, new Service("Women's Cut"), "Joan is an Artist! She has Magic in her hands and she knows how to bring the best out of you and enhance your beauty.", "- Susan B. | 30.03.2017"));
        reviews.add(new Review(5, new Service("Women's Cut"), "I love Joan! She is one of the finest hair stylist in los angeles. I have gotten haircuts from her for over 10 years, and continously always come back for more!", "- Mikki S. | 08.30.2016"));
    }

    protected User(Parcel in) {
        username = in.readString();
        email = in.readString();
        organization = in.readString();
        role = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(organization);
        dest.writeString(role);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public String getRole() {
        return role;
    }

    public List getServices() {
        return services;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
