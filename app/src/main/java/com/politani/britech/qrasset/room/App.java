package com.app.britech.riung.room;

import android.app.Application;
import android.arch.persistence.room.Room;


public class App extends Application {
    public static AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, AppDatabase.class, "aset-db").build();
    }
}
