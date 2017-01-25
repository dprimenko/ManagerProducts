package com.example.managerproducts.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.managerproducts.DatabaseManager;
import com.example.managerproducts.ManageProductApplication;
import com.example.managerproducts.schema.ManageProductContract;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by usuario on 20/01/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ManageProduct.db";
    /**
     * Se debe de poner volatile porque un hilo que este esperando
     * puede tener cacheado el valor null de la constante y volver a inicializarla
     * en getInstance()
     * **/
    private volatile static DatabaseHelper databaseHelper;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    private DatabaseHelper() {
        super(ManageProductApplication.getContext().getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        mOpenCounter = new AtomicInteger();
    }

    public synchronized static DatabaseHelper getInstance() {

        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper();
        }

        return databaseHelper;
    }

    public synchronized SQLiteDatabase openDatabase() {

        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = getWritableDatabase();
        }

        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db = DatabaseHelper.getInstance().openDatabase();
            db.beginTransaction();
            db.execSQL(ManageProductContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.Invoice.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceStatus.SQL_CREATE_ENTRIES);
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e("ManageProductDatabase: ", "Error al crear la base de datos -> " + e.getMessage());
        } finally {
            db.endTransaction();
            DatabaseHelper.getInstance().closeDatabase();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db = DatabaseHelper.getInstance().openDatabase();
            db.beginTransaction();
            db.execSQL(ManageProductContract.CategoryEntry.SQL_DROP_TABLE);
            db.execSQL(ManageProductContract.ProductEntry.SQL_DROP_TABLE);
            db.execSQL(ManageProductContract.PharmacyEntry.SQL_DROP_TABLE);
            db.execSQL(ManageProductContract.Invoice.SQL_DROP_TABLE);
            db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_DROP_TABLE);
            db.execSQL(ManageProductContract.InvoiceStatus.SQL_DROP_TABLE);
            db.setTransactionSuccessful();
            onCreate(db);
        } catch (SQLException e) {
            Log.e("ManageProductDatabase: ", "Error al actualizar la base de datos -> " + e.getMessage());
        } finally {
            db.endTransaction();
            DatabaseHelper.getInstance().closeDatabase();
        }


    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = DatabaseHelper.getInstance().openDatabase();
        onUpgrade(db, newVersion, oldVersion);
        DatabaseHelper.getInstance().closeDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db = DatabaseHelper.getInstance().openDatabase();
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
        DatabaseHelper.getInstance().closeDatabase();
    }
}
