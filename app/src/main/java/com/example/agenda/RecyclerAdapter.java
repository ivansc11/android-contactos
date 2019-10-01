package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<String> mContactImages;
    private ArrayList<String> mContactNumber;
    private ArrayList<String> mContactName;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)   
    public RecyclerAdapter(ArrayList<String> mContactImages, ArrayList<String> mContactNumber, ArrayList<String> mContactName, Context mContext) {
        this.mContactImages = mContactImages;
        this.mContactNumber = mContactNumber;
        this.mContactName = mContactName;
        this.mContext = mContext;
    }

     public RecyclerAdapter(ArrayList<String> mContactNumber, ArrayList<String> mContactName, Context mContext) {
         this.mContactNumber = mContactNumber;                                                                                                                 // Provide a reference to the views for each data item
         this.mContactName = mContactName;                                                                                                                     // Complex data items may need more than one view per item, and
         this.mContext = mContext;                                                                                                                             // you provide access to all the views for a data item in a view holder
     }                                                                                                                                                         public static class MyViewHolder extends RecyclerView.ViewHolder {
                                                                                                                                                                   // each data item is just a string in this case
        TextView textViewName;
        TextView textViewNumber;
        ImageView imageView;
        RelativeLayout parent;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.contact_image);
            textViewName = itemView.findViewById(R.id.contact_name);
            textViewNumber = itemView.findViewById(R.id.contact_number);
            parent = itemView.findViewById(R.id.parent_layout);
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewName.setText(mContactName.get(position));
        holder.textViewNumber.setText(mContactNumber.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mContactName.size();
    }
}
