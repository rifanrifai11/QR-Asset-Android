package com.app.britech.riung.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DataAsetDao {

    @Query("SELECT * FROM data_aset")
    List<DataAset> getAll();
    @Query("SELECT * FROM data_aset WHERE id = :id")
    DataAset getAll(long id);
    @Insert
    void create(DataAset dataAset);
    @Update
    void update(DataAset dataAset);
    @Delete
    void delete(DataAset dataAset);

    @Query("DELETE FROM data_aset")
    void emptyTable();
}
