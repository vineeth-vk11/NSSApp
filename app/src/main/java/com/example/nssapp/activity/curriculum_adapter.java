package com.example.nssapp.activity;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nssapp.R;

public class curriculum_adapter extends RecyclerView.Adapter<curriculum_adapter.curriculumViewHolder> {
    private String[] data;
    private Context context;
    public curriculum_adapter(String[] data,Context context)
    {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public curriculumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.clp_item, parent, false);
        return new curriculumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull curriculumViewHolder holder, int position) {
        String title = data[position];
        holder.txtTitle.setText(title);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class curriculumViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView txtTitle;

        public curriculumViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle =  itemView.findViewById(R.id.txtTitle);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (getAdapterPosition()){
                case 0:
                    intent =  new Intent(context, class6.class);
                    break;
                case 1:
                    intent =  new Intent(context, class7.class);
                    break;
                case 2:
                    intent =  new Intent(context, class8.class);
                    break;
                case 3:
                    intent =  new Intent(context, class9.class);
                    break;
                case 4:
                    intent =  new Intent(context, class10.class);
                    break;
            }
            context.startActivity(intent);
        }
    }

}
