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

import com.google.android.material.textfield.TextInputEditText;

public class MainDialogFragment extends DialogFragment {

    private TextInputEditText mNameInput;
    private TextInputEditText mPhoneInput;
    private TextInputEditText mEmailInput;

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String name, String phone, String email);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    private DialogListener mDialogListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDialogListener = (DialogListener) context;
    }

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
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialogListener.onDialogPositiveClick(MainDialogFragment.this,
                                mNameInput.getText().toString(),mPhoneInput.getText().toString(), mEmailInput.getText().toString());
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
}
