package com.example.managerproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usuario on 2/02/17.
 */

public class Category implements Parcelable {

    private int mId;
    private String mName;

    protected Category(Parcel in) {
        mId = in.readInt();
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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
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

    public Category(int mId, String mName) {
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
        dest.writeInt(mId);
        dest.writeString(mName);
    }
}
