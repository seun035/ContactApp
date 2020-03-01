package com.liadi.oluwaseun.contactapp.repository;

import android.content.Context;

import com.liadi.oluwaseun.contactapp.AppDatabase;
import com.liadi.oluwaseun.contactapp.models.Contact;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class ContactRepository {

   private AppDatabase mAppDatabase;

    public ContactRepository(Context context) {
        mAppDatabase = AppDatabase.getDatabaseInstance(context);
    }

    public void insertContact(final Contact contact) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.contactDao().insert(contact);
            }
        });

    }

    public void updateContact(final Contact contact) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.contactDao().update(contact);
            }
        });

    }

    public void deleteContact(final Contact contact) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mAppDatabase.contactDao().delete(contact);
            }
        });
    }

    public LiveData<List<Contact>> getAllContact() {
        return mAppDatabase.contactDao().getAllContact();
    }

    public Contact getContact(int contact_id) {
        return mAppDatabase.contactDao().getContact(contact_id);
    }
}
