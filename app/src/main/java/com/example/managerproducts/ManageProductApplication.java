package com.example.managerproducts;

import android.app.Application;
import android.provider.ContactsContract;

import com.example.managerproducts.dao.DatabaseHelper;

/**
 * Created by usuario on 20/01/17.
 */

public class ManageProductApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance(this).open();
    }
}
