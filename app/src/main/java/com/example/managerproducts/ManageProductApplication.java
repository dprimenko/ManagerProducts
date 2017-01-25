package com.example.managerproducts;

import android.app.Application;
import android.content.Context;

import com.example.managerproducts.dao.DatabaseHelper;

/**
 * Created by usuario on 20/01/17.
 */

public class ManageProductApplication extends Application {

    private static ManageProductApplication singleton;

    public ManageProductApplication() {
        singleton = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance().openDatabase();
    }

    public static Context getContext() {
        return singleton;
    }
}
