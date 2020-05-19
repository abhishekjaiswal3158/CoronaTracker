package com.example.networking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GlobalAdapter extends ArrayAdapter<Global> {


    public GlobalAdapter(Activity context, ArrayList<Global> global_object) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,global_object);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.global_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Global currentCorona = getItem(position);

        TextView state=(TextView)listItemView.findViewById(R.id.country);
        state.setText(currentCorona.getCountry());

        TextView total=(TextView)listItemView.findViewById(R.id.ctotal);
        total.setText(currentCorona.getTotal());

        TextView active=(TextView)listItemView.findViewById(R.id.cactive);
        active.setText(currentCorona.getActive());

        TextView recover=(TextView)listItemView.findViewById(R.id.crecovered);
        recover.setText(currentCorona.getRecover());

        TextView death=(TextView)listItemView.findViewById(R.id.cdeath);
        death.setText(currentCorona.getDeath());


        return listItemView;
    }

}
