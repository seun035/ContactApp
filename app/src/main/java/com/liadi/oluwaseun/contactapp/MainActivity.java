package com.liadi.oluwaseun.contactapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.liadi.oluwaseun.contactapp.adapter.ContactAdapter;
import com.liadi.oluwaseun.contactapp.models.Contact;
import com.liadi.oluwaseun.contactapp.viewmodel.ContactViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainDialogFragment.DialogListener {

    private FragmentManager fm;
    private ContactViewModel mContactViewModel;
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        mRecyclerView = findViewById(R.id.contact_recycler);
        mAdapter =  new ContactAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        fm = getSupportFragmentManager();
        mContactViewModel.getAllContact().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

                mAdapter.setContact(contacts);
                mAdapter.notifyDataSetChanged();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = (MainDialogFragment)MainDialogFragment.newInstance(null);
                dialog.show(fm , "main-dialog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveAddClick(DialogFragment dialog, String name, String phone, String email) {
        mContactViewModel.insertContact(new Contact(0,name,phone,email));
    }

    @Override
    public void onDialogPositiveUpdateClick(DialogFragment dialog, Contact contact) {
        mContactViewModel.updateContact(contact);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void showDialog(Contact contact) {

        DialogFragment dialog = (MainDialogFragment)MainDialogFragment.newInstance(contact);
        dialog.show(fm , "main-dialog");
    }
}
