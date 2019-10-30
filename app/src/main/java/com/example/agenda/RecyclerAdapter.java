
package com.example.agenda;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {


    private ArrayList<Contact> mContactsFull = new ArrayList<>();
    private ArrayList<Contact> mContacts;


    public RecyclerAdapter(Cursor queryRequest) {
        //Parses the data on the db and adds it to mContactsFull
        ParseData(queryRequest);
        mContacts = new ArrayList<>(mContactsFull);
    }

    public void ParseData(Cursor queryRequest) {

        String[] a = queryRequest.getColumnNames();

        for (queryRequest.moveToFirst(); !queryRequest.isAfterLast(); queryRequest.moveToNext()) {
            mContactsFull.add(
                    new Contact(
                            queryRequest.getInt(queryRequest.getColumnIndex("_id")),
                            queryRequest.getString(queryRequest.getColumnIndex("name")),
                            queryRequest.getString(queryRequest.getColumnIndex("surname")),
                            queryRequest.getString(queryRequest.getColumnIndex("phoneNumber")),
                            queryRequest.getString(queryRequest.getColumnIndex("birthday"))));

        }

    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        Contact contact;

        // each data item is just a string in this case
        TextView textViewName;
        TextView textViewSurname;
        TextView textViewNumber;
        ImageView imageView;
        RelativeLayout parent;
        ImageButton editButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.contact_image);
            textViewName = itemView.findViewById(R.id.contact_name);
            textViewSurname = itemView.findViewById(R.id.contact_surname);
            textViewNumber = itemView.findViewById(R.id.contact_number);
            parent = itemView.findViewById(R.id.parent_layout);
            editButton = itemView.findViewById(R.id.btnEdit);
        }

        public ImageButton getButton() {
            return editButton;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listitem, parent, false);


        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Context mContext = holder.itemView.getContext();


        holder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditContactActivity.class);
                intent.putExtra("editContact", holder.contact);
                mContext.startActivity(intent);
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                // Toast.makeText(ComplexRecyclerViewAdapter.this, "Item no: "+ position, Toast.LENGTH_LONG).show;
                Toast.makeText(v.getContext(), "General click !" + holder.textViewName.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + holder.textViewNumber.getText().toString()));

                mContext.startActivity(intent);
            }
        });

        holder.contact = mContacts.get(position);
        holder.textViewName.setText(mContacts.get(position).getName());
        //Modificar
        //holder.textViewName.setText(mContacts.get(position).getName()+" "+mContacts.get(position).getSurname());
        holder.textViewSurname.setText(mContacts.get(position).getSurname());
        holder.textViewNumber.setText(mContacts.get(position).getPhoneNumber());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mContactsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Contact contact : mContactsFull) {
                    if (contact.getName().concat(" " + contact.getSurname()).toLowerCase().contains(filterPattern)) {
                        filteredList.add(contact);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mContacts.clear();
            mContacts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}