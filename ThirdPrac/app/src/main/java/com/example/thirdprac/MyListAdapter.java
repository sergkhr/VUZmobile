package com.example.thirdprac;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<ListItem> {
    private LayoutInflater inflater;
    private int layout;
    private List<ListItem> items;

    public MyListAdapter(Context context, int resource, List<ListItem> items) {
        super(context, resource, items);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.itemImage);
        TextView text = (TextView) convertView.findViewById(R.id.itemText);

        ListItem item = items.get(position);

        int imageResource = convertView.getResources().getIdentifier(item.getImageResource(),
                "drawable", convertView.getContext().getPackageName());

        image.setImageResource(imageResource);
        text.setText(item.getText());

        return convertView;
    }
}
