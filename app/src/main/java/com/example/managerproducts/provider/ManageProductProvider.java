package com.example.managerproducts.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.managerproducts.db.DatabaseContract;
import com.example.managerproducts.db.DatabaseHelper;

/**
 * Created by usuario on 6/02/17.
 */

public class ManageProductProvider extends ContentProvider {

    private static final int PRODUCT = 1;
    private static final int PRODUCT_ID = 2;
    private static final int CATEGORY = 3;
    private static final int CATEGORY_ID = 4;
    private static final int INVOICE_STATUS = 5;
    private static final int INVOICE_STATUS_ID = 6;
    private static final int PHARMACY = 7;
    private static final int PHARMACY_ID = 8;
    private static final int INVOICE_LINE = 9;
    private static final int INVOICE_LINE_ID = 10;
    private static final int INVOICE = 11;
    private static final int INVOICE_ID = 12;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase sqLiteDatabase;

    static {
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Product.CONTENT_PATH, PRODUCT);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Category.CONTENT_PATH, CATEGORY);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatus.CONTENT_PATH, INVOICE_STATUS);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLine.CONTENT_PATH, INVOICE_LINE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Invoice.CONTENT_PATH, INVOICE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Pharmacy.CONTENT_PATH, PHARMACY);

        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Product.CONTENT_PATH + "/#", PRODUCT_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Category.CONTENT_PATH + "/#", CATEGORY_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatus.CONTENT_PATH + "/#", INVOICE_STATUS_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLine.CONTENT_PATH + "/#", INVOICE_LINE_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Invoice.CONTENT_PATH + "/#", INVOICE_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Pharmacy.CONTENT_PATH + "/#", PHARMACY_ID);

    }

    @Override
    public boolean onCreate() {
        sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        switch(uriMatcher.match(uri)) {
            case CATEGORY:
                sqLiteQueryBuilder.setTables(DatabaseContract.CategoryEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = DatabaseContract.CategoryEntry.DEFAULT_SORT;
                }
                break;
            case CATEGORY_ID:
                break;
            case PRODUCT:
                sqLiteQueryBuilder.setTables(DatabaseContract.ProductEntry.TABLE_NAME);
                break;
            case PRODUCT_ID:
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("URI invalid");
        }
        String sqlQuery = sqLiteQueryBuilder.buildQuery(projection, selection, null, null, sortOrder, null);
        Log.i("manageproductcontent", sqlQuery);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Uri newUri = null;

        long regId = -1;

        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                regId = sqLiteDatabase.insert(DatabaseContract.CategoryEntry.TABLE_NAME, null, values);
                break;
            case PRODUCT:
                regId = sqLiteDatabase.insert(DatabaseContract.ProductEntry.TABLE_NAME, null, values);
                break;
        }

        newUri = ContentUris.withAppendedId(uri, regId);

        if (regId != -1) {
            getContext().getContentResolver().notifyChange(newUri, null); // No tenemos ninguna clase observer
        } else {
            throw new SQLException("CR: Insert error");
        }
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        Uri newUri = null;
        int regId = -1;

        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                regId = sqLiteDatabase.delete(DatabaseContract.CategoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PRODUCT:
                regId = sqLiteDatabase.delete(DatabaseContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;
        }

        newUri = ContentUris.withAppendedId(uri, regId);

        if (regId != -1) {
            getContext().getContentResolver().notifyChange(newUri, null); // No tenemos ninguna clase observer
        } else {
            throw new SQLException("CR: Delete error");
        }

        return regId;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Uri newUri = null;
        int regId = -1;

        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                regId = sqLiteDatabase.update(DatabaseContract.CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case PRODUCT:
                regId = sqLiteDatabase.update(DatabaseContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
        }

        newUri = ContentUris.withAppendedId(uri, regId);

        if (regId != -1) {
            getContext().getContentResolver().notifyChange(newUri, null); // No tenemos ninguna clase observer
        } else {
            throw new SQLException("CR: No columns detected");
        }

        return regId;
    }
}
