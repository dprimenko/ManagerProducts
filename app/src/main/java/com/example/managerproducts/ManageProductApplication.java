package com.example.managerproducts;

import android.app.Application;
import android.content.Context;

import com.example.managerproducts.dao.DatabaseHelper;

/**
 * Created by usuario on 20/01/17.
 */

public class ManageProductApplication extends Application {

    private static ManageProductApplication application;

    private ManageProductApplication () {
    }

    public static ManageProductApplication getInstance() {

        if (application == null) {
            application = new ManageProductApplication();
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance().open();
    }

    public Context getContext() {
        return this;
    }
}
