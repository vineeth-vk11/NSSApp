package com.example.nssapp.activity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nssapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListner.onItemClick(view,getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListner.onItemlongClick(view,getAdapterPosition());
                return false;
            }
        });

    }

    public void setDetails(Context ctx,String date){
        TextView mDatetv = mView.findViewById(R.id.txtdate);

        mDatetv.setText(date);
    }

    private ViewHolder.ClickListner mClickListner;

    public interface ClickListner
    {
        void onItemClick(View view,int position);
        void onItemlongClick(View view,int position);

    }

    public void setOnClickListner(ViewHolder.ClickListner clickListner){
        mClickListner = clickListner;
    }
}
