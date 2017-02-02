package com.example.managerproducts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.managerproducts.dao.DatabaseHelper;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Pharmacy;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.schema.ManageProductContract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    public List<Category> getAllCategories() {
        List<Category> categoriesList = new ArrayList<>();

        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(ManageProductContract.CategoryEntry.SQL_SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setmId(String.valueOf(cursor.getInt(cursor.getColumnIndex(ManageProductContract.CategoryEntry._ID))));
                category.setmName(cursor.getString(cursor.getColumnIndex(ManageProductContract.CategoryEntry.COLUMN_NAME)));

                categoriesList.add(category);
            } while (cursor.moveToNext());
        }

        return categoriesList;
    }

    public List<Pharmacy> getPharmacies() {
        List<Pharmacy> pharmaciesList = new ArrayList<>();

        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(ManageProductContract.CategoryEntry.SQL_SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                Pharmacy pharmacy = new Pharmacy();
                pharmacy.setmId(String.valueOf(cursor.getInt(cursor.getColumnIndex(ManageProductContract.CategoryEntry._ID))));
                pharmacy.setmCif(cursor.getString(cursor.getColumnIndex(ManageProductContract.PharmacyEntry.COLUMN_CIF)));
                pharmacy.setmAddress(cursor.getString(cursor.getColumnIndex(ManageProductContract.PharmacyEntry.COLUMN_ADDRESS)));
                pharmacy.setmPhoneNumber(cursor.getString(cursor.getColumnIndex(ManageProductContract.PharmacyEntry.COLUMN_PHONENUMBER)));
                pharmacy.setmEmail(cursor.getString(cursor.getColumnIndex(ManageProductContract.PharmacyEntry.COLUMN_EMAIL)));
                pharmaciesList.add(pharmacy);
            } while (cursor.moveToNext());
        }

        return pharmaciesList;
    }

    public int insertPharmacy(Pharmacy pharmacy) {
        int result;
        SQLiteDatabase db = null;
        ContentValues contentValues = new ContentValues();

        db = DatabaseHelper.getInstance().openDatabase();

        contentValues.put(ManageProductContract.PharmacyEntry.COLUMN_CIF, pharmacy.getmCif());
        contentValues.put(ManageProductContract.PharmacyEntry.COLUMN_ADDRESS, pharmacy.getmAddress());
        contentValues.put(ManageProductContract.PharmacyEntry.COLUMN_PHONENUMBER, pharmacy.getmPhoneNumber());
        contentValues.put(ManageProductContract.PharmacyEntry.COLUMN_EMAIL, pharmacy.getmEmail());

        result = (int)db.insert(ManageProductContract.PharmacyEntry.TABLE_NAME, null, contentValues);
        DatabaseHelper.getInstance().closeDatabase();
        Log.d("ResultPharmacy: ", String.valueOf(result));
        return result;
    }

    public int insertCategory(String category) {

        int result;
        SQLiteDatabase db = null;
        db = DatabaseHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();

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
