package com.example.managerproducts.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.managerproducts.ManageProductApplication;
import com.example.managerproducts.provider.ManageProductContract;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by usuario on 20/01/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 32;
    private static final String DATABASE_NAME = "manageproduct.db";
    /**
     * Se debe de poner volatile porque un hilo que este esperando
     * puede tener cacheado el valor null de la constante y volver a inicializarla
     * en getInstance()
     * **/
    private volatile static DatabaseHelper databaseHelper;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    private DatabaseHelper() {
        super(ManageProductApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
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
            db.beginTransaction();
            db.execSQL(DatabaseContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceStatusEntry.SQL_CREATE_ENTRIES);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e("ManageProductDatabase: ", "Error al crear la base de datos -> " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            db.execSQL(DatabaseContract.CategoryEntry.SQL_DROP_TABLE);
            db.execSQL(DatabaseContract.ProductEntry.SQL_DROP_TABLE);
            db.execSQL(DatabaseContract.PharmacyEntry.SQL_DROP_TABLE);
            db.execSQL(DatabaseContract.InvoiceEntry.SQL_DROP_TABLE);
            db.execSQL(DatabaseContract.InvoiceLineEntry.SQL_DROP_TABLE);
            db.execSQL(DatabaseContract.InvoiceStatusEntry.SQL_DROP_TABLE);
            db.setTransactionSuccessful();
            db.endTransaction();
            onCreate(db);
        } catch (SQLException e) {
            Log.e("ManageProductDatabase: ", "Error al actualizar la base de datos -> " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.beginTransaction();
                db.execSQL("PRAGMA foreign_keys = ON");
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
