package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import viewmodels.contact;

@Dao
public interface contactDao {
    @Query("SELECT*FROM contact")
    List<contact> index();
    @Query("SELECT*FROM contact WHERE id =:id")
    contact get(int id);
    @Insert
    void insert(contact ...contacts);
    @Update
    void update(contact ...contacts);
    @Delete
    void delete(contact ...contacts);
}
