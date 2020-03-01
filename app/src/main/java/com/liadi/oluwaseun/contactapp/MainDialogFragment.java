package com.liadi.oluwaseun.contactapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.liadi.oluwaseun.contactapp.models.Contact;

public class MainDialogFragment extends DialogFragment {

    private TextInputEditText mNameInput;
    private TextInputEditText mPhoneInput;
    private TextInputEditText mEmailInput;
    private boolean isAddContact = true;
    private String dialogTitle = "Add Contact";
    private Contact contact;

    public interface DialogListener {
        public void onDialogPositiveAddClick(DialogFragment dialog, String name, String phone, String email);
        public void onDialogPositiveUpdateClick(DialogFragment dialog, Contact contact);
        public void onDialogNegativeClick(DialogFragment dialog);
        public void showDialog(Contact contact);
    }

    private DialogListener mDialogListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDialogListener = (DialogListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = (Contact) getArguments().getSerializable("Contact");
        if (contact != null){
            isAddContact = false;
            dialogTitle = "Update Contact";
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_dialog, null, false);
        mNameInput = view.findViewById(R.id.contact_name);
        mPhoneInput = view.findViewById(R.id.contact_phone);
        mEmailInput = view.findViewById(R.id.contact_email);

        if (!isAddContact) {
            mNameInput.setText(contact.getName());
            mPhoneInput.setText(contact.getPhone_no());
            mEmailInput.setText(contact.getEmail());
        }
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(dialogTitle)
                .setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isAddContact) {
                            mDialogListener.onDialogPositiveAddClick(MainDialogFragment.this,
                                    mNameInput.getText().toString(),mPhoneInput.getText().toString(), mEmailInput.getText().toString());
                        }
                        else {
                            contact.setEmail(mEmailInput.getText().toString());
                            contact.setPhone_no(mPhoneInput.getText().toString());
                            contact.setName(mNameInput.getText().toString());
                            mDialogListener.onDialogPositiveUpdateClick(MainDialogFragment.this, contact);
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mNameInput.setText("");
                        mPhoneInput.setText("");
                        mEmailInput.setText("");
                        dialogInterface.cancel();
                    }
                }).create();

        return alertDialog;
    }

    public static Fragment newInstance(Contact contact) {

        Bundle args = new Bundle();
        args.putSerializable("Contact",contact);
        Fragment fragment = new MainDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
