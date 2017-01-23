package com.example.managerproducts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.managerproducts.dao.DatabaseHelper;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.schema.ManageProductContract;

import java.util.List;

/**
 * Created by usuario on 23/01/17.
 */

public class DatabaseManager {

    private static DatabaseManager databaseManager;

    private DatabaseManager () {
    }

    public static DatabaseManager getInstance() {

        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public List<Product> getAllProducts() {
        return null;
    }

    public int insertProduct(Product product) {
        int result;
        SQLiteDatabase db = null;
        ContentValues contentValues = null;

        db = DatabaseHelper.getInstance().openDatabase();
        db.beginTransaction();
        contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getmConcentration());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getmImage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, 1);

        result = (int)db.insert(
                ManageProductContract.ProductEntry.TABLE_NAME,
                null,
                contentValues);

        return result;
    }

    public int updateProduct(Product product) {
        int result;
        SQLiteDatabase db = null;
        ContentValues contentValues = null;

        db = DatabaseHelper.getInstance().openDatabase();
        db.beginTransaction();
        contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getmConcentration());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getmImage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, 1);

        result = (int)db.update(
                ManageProductContract.ProductEntry.TABLE_NAME,
                contentValues,
                ManageProductContract.ProductEntry._ID,
                new String[]{product.getmId()});

        return result;
    }

    public int deleteProduct(Product product) {
        int result;
        SQLiteDatabase db = null;

        db = DatabaseHelper.getInstance().openDatabase();
        db.beginTransaction();

        result = db.delete(
                ManageProductContract.ProductEntry.TABLE_NAME,
                ManageProductContract.ProductEntry._ID,
                new String[]{product.getmId()});

        return result;
    }
}
