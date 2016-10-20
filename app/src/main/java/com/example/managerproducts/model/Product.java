package com.example.managerproducts.model;

/**
 * Created by David Primenko on 19/10/16.
 */

/*
 * Class Product
 */

public class Product {

    //region Fields
    private String mId;
    private String mName;
    private String mDescription;
    private String mBrand;
    private String mConcentration;
    private double mPrice;
    private int mStock;
    private int mImage;
    //endregion

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
    //endregion

    //region OverrideMethods

    /*
    * Dos productos son iguales cuando tienen el mismo nombre, marca y concetracion
    */

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this != o) {
            if (o instanceof Product) {
                Product product = (Product) o;

                if ((this.mName.equals(product.getmName())) &&
                        (this.mBrand.equals(product.getmBrand())) &&
                        (this.mConcentration.equals(product.getmConcentration()))) {
                    result = true;
                }
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
    //endregion


}
