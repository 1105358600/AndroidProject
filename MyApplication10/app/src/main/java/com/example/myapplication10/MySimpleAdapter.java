package com.example.myapplication10;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends SimpleAdapter {
    private ArrayList<Map<String, String>> mData;

    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data,
                           int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.mData = (ArrayList<Map<String, String>>)data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int mPosition = position;
        return super.getView(position, convertView, parent);
    }
}