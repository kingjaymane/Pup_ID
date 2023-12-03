package com.example.flowerid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class ButtonAdapter extends ArrayAdapter<String> {

    private ArrayList<String> dataList;
    private LayoutInflater inflater;

    public ButtonAdapter(Context context, ArrayList<String> data) {
        super(context, 0, data);
        this.dataList = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.button_item, parent, false);
        }

        Button button = view.findViewById(R.id.button);
        button.setText(dataList.get(position));

        return view;
    }
}
