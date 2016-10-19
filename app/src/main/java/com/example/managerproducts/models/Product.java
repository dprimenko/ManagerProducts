package com.example.managerproducts.models;

/**
 * Created by David Primenko on 19/10/16.
 */

/*
 * Class Product
 */

public class Product {

    //region Fields
    private int mId;
    private String mName;
    private String mDescription;
    private String mBrand;
    private double mPrice;
    private int mStock;
    private int mImage;
    //endregion

    //region Getter&Setter
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

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getmStock() {
        return mStock;
    }

    public void setmStock(int mStock) {
        this.mStock = mStock;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }
    //endregion

    //region OverrideMethods
    @Override
    public String toString() {
        return "Product{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mBrand='" + mBrand + '\'' +
                ", mPrice=" + mPrice +
                ", mStock=" + mStock +
                ", mImage=" + mImage +
                '}';
    }
    //endregion

    //region Constructor
    public Product(String mName, String mDescription, String mBrand, double mPrice, int mStock, int mImage) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mBrand = mBrand;
        this.mPrice = mPrice;
        this.mStock = mStock;
        this.mImage = mImage;
    }
    //endregion


}
