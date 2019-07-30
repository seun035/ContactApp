package com.liadi.oluwaseun.contactapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

public class MainDialogFragment extends DialogFragment {

    private TextInputEditText mNameInput;
    private TextInputEditText mPhoneInput;
    private TextInputEditText mEmailInput;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_dialog, null, false);
        mNameInput = view.findViewById(R.id.contact_name);
        mPhoneInput = view.findViewById(R.id.contact_phone);
        mEmailInput = view.findViewById(R.id.contact_email);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Add Contact")
                .setView(view)
                .setPositiveButton("Add",null)
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
}
