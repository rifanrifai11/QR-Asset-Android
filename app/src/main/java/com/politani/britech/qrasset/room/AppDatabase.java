package com.app.britech.riung.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DataAset.class, AsetTaking.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataAsetDao getDataAsetDao();

    public  abstract  AsetTakingDao getAsetTakingDao();
}
