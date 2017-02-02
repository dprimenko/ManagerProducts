package com.example.managerproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usuario on 2/02/17.
 */

public class Category implements Parcelable {

    private String mId;
    private String mName;

    protected Category(Parcel in) {
        mId = in.readString();
        mName = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Category() {
    }

    public Category(String mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
    }
}
