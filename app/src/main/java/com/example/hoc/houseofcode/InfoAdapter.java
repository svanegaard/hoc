package com.example.hoc.houseofcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;

public class InfoAdapter extends BaseAdapter {

    private final Context context;
    private LinkedHashMap<String, String> mData = new LinkedHashMap<String, String>();
    private String[] mKeys;

    public InfoAdapter(Context context, LinkedHashMap<String, String> map) {
        this.context = context;
        mData  = map;
        mKeys = mData.keySet().toArray(new String[map.size()]);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(mKeys[position]);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_row, parent, false);
        } else {
            result = convertView;
        }

        TextView txtLabel= (TextView) result.findViewById(R.id.label);
        TextView txtValue = (TextView) result.findViewById(R.id.value);

        String key = mKeys[position];
        String value = getItem(position).toString();

        txtLabel.setText(key);
        txtValue.setText(value);

        return result;
    }
}