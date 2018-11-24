package com.app.britech.riung.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AsetTakingDao {

    @Query("SELECT * FROM aset_taking")
    List<DataAset> getAll();
    @Query("SELECT * FROM aset_taking WHERE id = :id")
    DataAset getAll(long id);
    @Insert
    void create(AsetTaking asetTaking);
    @Update
    void update(AsetTaking asetTaking);
    @Delete
    void delete(AsetTaking asetTaking);
}
