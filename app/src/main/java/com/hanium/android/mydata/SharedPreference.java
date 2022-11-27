package com.hanium.android.mydata;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedPreference {

    static final String TAG = "LoginActivity";

    static final String PREF_USER_ID = "userID";
    static final String PREF_USER_NAME = "userName";
    static final String PREF_USER_PW = "userPW";
    static final String PREF_USER_EMAIL = "userEmail";
    static final String PREF_USER_POINT = "userPoint";


    static SharedPreferences getSharedPreference (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserName (Context context, String userName) {
        Log.e(TAG, "user name : " +userName);
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName (Context context) {
        return getSharedPreference(context).getString(PREF_USER_NAME, "");
    }


    public static void setUserID (Context context, String userID) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(PREF_USER_ID, userID);
        editor.commit();
    }

    public static String getUserID (Context context) {
        return getSharedPreference(context).getString(PREF_USER_ID, "");
    }


    public static void setUserPW (Context context, String userPW) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(PREF_USER_PW, userPW);
        editor.commit();
    }

    public static String getUserPW(Context context) {
        return getSharedPreference(context).getString(PREF_USER_PW, "");
    }


    public static void setUserEmail (Context context, String userEmail) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(PREF_USER_EMAIL, userEmail);
        editor.commit();
    }

    public static String getUserEmail(Context context) {
        return getSharedPreference(context).getString(PREF_USER_EMAIL, "");
    }


    public static void setUserPoint (Context context, Integer userPoint) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(PREF_USER_POINT, userPoint);
        editor.commit();
    }

    public static int getUserPoint(Context context) {
        return getSharedPreference(context).getInt(PREF_USER_POINT, 0);
    }



    public static void clearUser(Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.clear();
        editor.commit();
    }
}
