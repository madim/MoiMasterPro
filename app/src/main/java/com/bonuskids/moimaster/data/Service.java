package com.bonuskids.moimaster.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by madi on 4/8/17.
 */

public class Service implements Parcelable {

    private String title;
    private String pricePerMinute;

    public Service() {

    }

    public Service(String title) {
        this.title = title;
    }

    public Service(String title, String pricePerMinute) {
        this.title = title;
        this.pricePerMinute = pricePerMinute;
    }

    public String getTitle() {
        return title;
    }

    public String getPricePerMinute() {
        return pricePerMinute;
    }

    protected Service(Parcel in) {
        title = in.readString();
        pricePerMinute = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(pricePerMinute);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };
}
