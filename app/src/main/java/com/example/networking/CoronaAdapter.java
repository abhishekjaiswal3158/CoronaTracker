package com.example.networking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class CoronaAdapter extends ArrayAdapter<Corona> {


    public CoronaAdapter(Activity context, ArrayList<Corona> corona_object) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,corona_object);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the {@link AndroidFlavor} object located at this position in the list
            Corona currentCorona = getItem(position);

       TextView state=(TextView)listItemView.findViewById(R.id.state);
       state.setText(currentCorona.getState());



        TextView active=(TextView)listItemView.findViewById(R.id.active);
        active.setText(currentCorona.getActive());




        return listItemView;
    }

}