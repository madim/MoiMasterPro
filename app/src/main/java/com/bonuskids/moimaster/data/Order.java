package com.bonuskids.moimaster.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by madi on 4/7/17.
 */

public class Order implements Parcelable {

    private int orderId;
    private int userId;
    private String title;
    private String description;
    private String photoUrl;
    private int price;
    private long timestamp;
    private String date;

    public Order() {

    }

    public Order(String title, String description) {
        this.title = title;
        this.description = description;
        this.date = "22 TUE, 10 AM";
    }

    protected Order(Parcel in) {
        orderId = in.readInt();
        userId = in.readInt();
        title = in.readString();
        description = in.readString();
        photoUrl = in.readString();
        price = in.readInt();
        timestamp = in.readLong();
        date = in.readString();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return date;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(orderId);
        parcel.writeInt(userId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(photoUrl);
        parcel.writeInt(price);
        parcel.writeLong(timestamp);
        parcel.writeString(date);
    }
}
