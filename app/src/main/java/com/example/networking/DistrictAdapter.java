package com.example.networking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DistrictAdapter extends ArrayAdapter<DistrictCorona> {

    public DistrictAdapter(Activity context, ArrayList<DistrictCorona> corona) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,corona);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.district_item, parent, false);
        }


        DistrictCorona currentCorona = getItem(position);

        TextView district=(TextView)listItemView.findViewById(R.id.district);
        district.setText(currentCorona.getDistrict());



        TextView active=(TextView)listItemView.findViewById(R.id.dactive);
        active.setText(currentCorona.getActive());




        return listItemView;
    }



}
