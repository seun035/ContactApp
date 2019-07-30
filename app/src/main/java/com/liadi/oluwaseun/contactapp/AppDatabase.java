package com.liadi.oluwaseun.contactapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.liadi.oluwaseun.contactapp.Dao.ContactDao;
import com.liadi.oluwaseun.contactapp.models.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private AppDatabase dbInstance;
    public abstract ContactDao contactDao();

    public synchronized AppDatabase getDatabaseInstance(Context contxet) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(contxet.getApplicationContext(),AppDatabase.class,"contact.db").build();
        }
        return dbInstance;
    }

}
