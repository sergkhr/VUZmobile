package com.example.thirdprac;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private final List<ListItem> items;

    public MyRecyclerAdapter(Context context, List<ListItem> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {
        ListItem item = items.get(position);
        int imageResource = holder.image.getResources().getIdentifier(item.getImageResource(),
                "drawable", holder.image.getContext().getPackageName());
        holder.image.setImageResource(imageResource);
        holder.text.setText(item.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked on " + position, Toast.LENGTH_SHORT).show();
                Log.d("MyRecyclerAdapter", "Clicked on " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView text;
        final ImageView image;
        public MyViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.itemText);
            image = (ImageView) view.findViewById(R.id.itemImage);
        }
    }
}
