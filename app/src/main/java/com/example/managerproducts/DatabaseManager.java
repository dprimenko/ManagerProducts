package com.example.managerproducts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.managerproducts.dao.DatabaseHelper;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.schema.ManageProductContract;

import java.util.ArrayList;
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

        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(ManageProductContract.ProductEntry.SQL_SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setmId(String.valueOf(cursor.getInt(cursor.getColumnIndex(ManageProductContract.ProductEntry._ID))));
                product.setmName(cursor.getString(cursor.getColumnIndex(ManageProductContract.ProductEntry.COLUMN_NAME)));
                product.setmDescription(cursor.getString(cursor.getColumnIndex(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION)));
                product.setmBrand(cursor.getString(cursor.getColumnIndex(ManageProductContract.ProductEntry.COLUMN_BRAND)));
                product.setmConcentration(cursor.getString(cursor.getColumnIndex(ManageProductContract.ProductEntry.COLUMN_DOSAGE)));
                product.setmPrice(cursor.getDouble(cursor.getColumnIndex(ManageProductContract.ProductEntry.COLUMN_PRICE)));
                product.setmStock(cursor.getInt(cursor.getColumnIndex(ManageProductContract.ProductEntry.COLUMN_STOCK)));
                product.setmImage(cursor.getString(cursor.getColumnIndex(ManageProductContract.ProductEntry.COLUMN_IMAGE)));

                productList.add(product);
            } while (cursor.moveToNext());
        }

        return productList;
    }

    public int insertCategory(String category) {

        int result;
        SQLiteDatabase db = null;
        ContentValues contentValues = new ContentValues();

        db = DatabaseHelper.getInstance().openDatabase();

        contentValues.put(ManageProductContract.CategoryEntry.COLUMN_NAME, category);

        result = (int)db.insert(ManageProductContract.CategoryEntry.TABLE_NAME, null, contentValues);
        DatabaseHelper.getInstance().closeDatabase();
        Log.d("ResultCat: ", String.valueOf(result));
        return result;
    }

    public int insertProduct(Product product) {
        int result;
        SQLiteDatabase db = null;
        ContentValues contentValues = null;

        db = DatabaseHelper.getInstance().openDatabase();
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
        DatabaseHelper.getInstance().closeDatabase();
        Log.d("Result: ", String.valueOf(result));
        return result;
    }

    public int updateProduct(Product product) {
        int result;
        SQLiteDatabase db = null;
        ContentValues contentValues = null;

        db = DatabaseHelper.getInstance().openDatabase();
        //db.beginTransaction();
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
        //db.beginTransaction();

        result = db.delete(
                ManageProductContract.ProductEntry.TABLE_NAME,
                ManageProductContract.ProductEntry._ID,
                new String[]{product.getmId()});

        return result;
    }
}
