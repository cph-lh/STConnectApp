package com.example.stconnectapp.Model;

import android.content.Context;

public class Helper {

    static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context c) {
        context = c;
    }
}
