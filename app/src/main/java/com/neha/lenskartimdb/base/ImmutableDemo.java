package com.neha.lenskartimdb.base;

import android.util.Log;

public final class ImmutableDemo {
    private static final String TAG = "ImmutableDemo";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public ImmutableDemo(String name){
        this.name = name;
    }

    public void display(){
        Log.d(TAG, "display: "+name);
    }
}
