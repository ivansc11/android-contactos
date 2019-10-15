package com.example.agenda;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Cursor queryRequest;




    private ArrayList<Contact> mContacts = new ArrayList<>();

    private ArrayList<String> mContactImages;
    private ArrayList<String> mContactName;
    private ArrayList<String> mContactSurname;
    private ArrayList<String> mContactNumber;


    // Provide a suitable constructor (depends on the kind of dataset)   
    public RecyclerAdapter(ArrayList<String> mContactImages, ArrayList<String> mContactNumber, ArrayList<String> mContactName) {
        this.mContactImages = mContactImages;
        this.mContactNumber = mContactNumber;
        this.mContactName = mContactName;
    }

    public RecyclerAdapter(Cursor queryRequest) {
        this.queryRequest = queryRequest;
        ParseData(queryRequest);
    }

    public void ParseData(Cursor queryRequest){


        String[] a = queryRequest.getColumnNames();

        for(queryRequest.moveToFirst(); !queryRequest.isAfterLast(); queryRequest.moveToNext())
        {
            mContacts.add(
                    new Contact(

                            queryRequest.getString(queryRequest.getColumnIndex("name")),
                    queryRequest.getString(queryRequest.getColumnIndex("surname")),
                    queryRequest.getString(queryRequest.getColumnIndex("phoneNumber")),
                    queryRequest.getString(queryRequest.getColumnIndex("birthday"))));

        }

    }

    public RecyclerAdapter(ArrayList<String> mContactNumber, ArrayList<String> mContactName) {
         this.mContactNumber = mContactNumber;
         // Provide a reference to the views for each data item
         this.mContactName = mContactName;
         // Complex data items may need more than one view per item, and

         // you provide access to all the views for a data item in a view holder
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

        public ImageButton getButton(){
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


        Log.d("Creating","Binding elements");
        holder.contact = mContacts.get(position);

        holder.textViewName.setText(mContacts.get(position).getName());
        holder.textViewSurname.setText(mContacts.get(position).getSurname());
        holder.textViewNumber.setText(mContacts.get(position).getPhoneNumber());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mContacts.size();
    }



}
