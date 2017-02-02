package com.example.managerproducts.model;

/**
 * Created by usuario on 2/02/17.
 */

public class Pharmacy {

    private String mId;
    private String mCif;
    private String mAddress;
    private String mPhoneNumber;
    private String mEmail;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmCif() {
        return mCif;
    }

    public void setmCif(String mCif) {
        this.mCif = mCif;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public Pharmacy() {
    }

    public Pharmacy(String mId, String mCif, String mAddress, String mPhoneNumber, String mEmail) {
        this.mId = mId;
        this.mCif = mCif;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
        this.mEmail = mEmail;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "mId='" + mId + '\'' +
                ", mCif='" + mCif + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", mEmail='" + mEmail + '\'' +
                '}';
    }
}
