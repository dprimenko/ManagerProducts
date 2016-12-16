package com.example.managerproducts.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.managerproducts.interfaces.IPreferences;

/**
 * Created by dprimenko on 10/11/16.
 */

public class AccountPreference implements IPreferences{

    private static IPreferences accountPreference;
    private Context context;

    //public static final String FILE = "com.danielacedo.manageproductrecycler_preferences";

    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private SharedPreferences sharedPreferences;


    private AccountPreference(Context con){
        context = con;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Singleton
    public static IPreferences getInstance(Context con){
        if(accountPreference == null){
            accountPreference = new AccountPreference(con);
        }

        return accountPreference;
    }

    public void putUser(String user){
        getEditor().putString(USER, user).apply();
    }

    public void putPassword(String password){
        getEditor().putString(PASSWORD, password).apply();
    }

    /*public String readUser(){
        return getEditor().putString(USER, "");
    }
    public String readPassword(){
        return getEditor().putString(PASSWORD, );
    }*/

    private SharedPreferences.Editor getEditor(){
        return sharedPreferences.edit();
    }
}
