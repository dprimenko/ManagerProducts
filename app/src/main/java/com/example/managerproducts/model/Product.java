package com.example.managerproducts.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.Locale;

/**
 * Created by David Primenko on 19/10/16.
 */

/*
 * Class Product
 */

public class Product implements Comparable<Product>, Parcelable{

    //region Fields
    private String mId;
    private String mName;
    private String mDescription;
    private String mBrand;
    private String mConcentration;
    private double mPrice;
    private int mStock;
    private int mImage;
    public static final Comparator<Product> PRICE_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {

            return Double.compare(p1.getmPrice(), p2.getmPrice());
        }
    };
    public static final Comparator<Product> STOCK_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getmStock() - p2.getmStock();
        }
    };
    //endregion

    protected Product(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mDescription = in.readString();
        mBrand = in.readString();
        mConcentration = in.readString();
        mPrice = in.readDouble();
        mStock = in.readInt();
        mImage = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    //region Getter&Setter
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

    public String getmConcentration() {
        return mConcentration;
    }

    public void setmConcentration(String mConcentration) {
        this.mConcentration = mConcentration;
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

    public String getFormattedPrice() {
        return String.format("%s€", mPrice);
    }

    public String getFormattedStock() {
        return String.format(Locale.getDefault(), "%d u.", mStock);
    }
    //endregion

    //region OverrideMethods

    /*
    * Dos productos son iguales cuando tienen el mismo nombre, marca
    */

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Product) {
            Product product = (Product) o;

            if ((this.mName.equals(product.getmName())) &&
                    (this.mBrand.equals(product.getmBrand())) &&
                    (this.mConcentration.equals(product.getmConcentration()))) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mDescription.hashCode();
        result = 31 * result + mBrand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return mName + " " + mConcentration + " Mg";
    }
    //endregion

    //region Constructor
    public Product(String mName, String mDescription, String mBrand, String mConcentration, double mPrice, int mStock, int mImage) {
        //mId = UUID.randomUUID().toString();
        this.mName = mName;
        this.mDescription = mDescription;
        this.mBrand = mBrand;
        this.mConcentration = mConcentration;
        this.mPrice = mPrice;
        this.mStock = mStock;
        this.mImage = mImage;
    }

    @Override
    public int compareTo(Product p) {
        if (this.getmName().compareTo(p.getmName()) == 0) {
            return this.getmBrand().compareTo(p.getmBrand());
        }
        else {
            return this.getmName().compareTo(p.getmName());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mBrand);
        dest.writeString(mConcentration);
        dest.writeDouble(mPrice);
        dest.writeInt(mStock);
        dest.writeInt(mImage);
    }
    //endregion


}
