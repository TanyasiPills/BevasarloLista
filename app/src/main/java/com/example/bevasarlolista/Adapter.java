package com.example.bevasarlolista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<Item> {

    public Adapter(Context context, List<Item> items) {
        super(context, R.layout.list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Item item = getItem(position);

        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemCount = convertView.findViewById(R.id.item_count);

        itemName.setText(item.getName());
        itemCount.setText(String.valueOf(item.getCount()));

        return convertView;
    }
}
