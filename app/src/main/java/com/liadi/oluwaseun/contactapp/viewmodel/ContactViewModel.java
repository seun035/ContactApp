package com.liadi.oluwaseun.contactapp.viewmodel;

import android.app.Application;

import com.liadi.oluwaseun.contactapp.models.Contact;
import com.liadi.oluwaseun.contactapp.repository.ContactRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactViewModel extends AndroidViewModel {

    private LiveData<List<Contact>> allContact;
    private ContactRepository mContactRepository;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepository(application);
    }


    public LiveData<List<Contact>> getAllContact() {
        if (allContact == null) {
            allContact = new MutableLiveData<>();
            allContact = mContactRepository.getAllContact();
        }
        return allContact;
    }

    public void insertContact(Contact contact) {
        mContactRepository.insertContact(contact);
    }

    public void updateContact(Contact contact) {
        mContactRepository.updateContact(contact);
    }

    public void deleteContact(Contact contact) {
        mContactRepository.deleteContact(contact);
    }
}
