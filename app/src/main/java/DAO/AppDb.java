package DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import viewmodels.contact;

@Database(entities = {contact.class}, version = 1)
public abstract class AppDb extends RoomDatabase{
    public abstract contactDao contactDao();
}
