package com.liadi.oluwaseun.contactapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liadi.oluwaseun.contactapp.R;
import com.liadi.oluwaseun.contactapp.models.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mContext;

    private List<Contact> mContact;

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(mContext).inflate(R.layout.contact_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bindContact(mContact.get(position));
    }

    @Override
    public int getItemCount() {
        return mContact != null ? mContact.size() : 0;
    }

    public void setContact(List<Contact> mContact) {
        this.mContact = mContact;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mEmailTextView;
        private TextView mPhoneTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.contact_item_name);
            mEmailTextView = itemView.findViewById(R.id.contact_item_email);
            mPhoneTextView = itemView.findViewById(R.id.contact_item_phone);
        }

        public void bindContact(Contact contact){
            mNameTextView.setText(contact.getName());
            mEmailTextView.setText(contact.getEmail());
            mPhoneTextView.setText(contact.getPhone_no());
        }
    }
}
