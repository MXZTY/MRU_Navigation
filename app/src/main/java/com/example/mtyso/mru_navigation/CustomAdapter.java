package com.example.mtyso.mru_navigation;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{

    final Context c;
    String[] values;

    //CONSTRUCTOR
    public CustomAdapter(Context context, String[] values) {
        super(context,R.layout.popup, values);
        this.c=context;
        this.values=values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //INFLATE OUR XML LAYOUT TO ROW
        View row=inflator.inflate(R.layout.popup, parent,false);

        //DECLARE FIELDS CONTAINED IN OUR LAYOUT
        TextView tv=(TextView) row.findViewById(R.id.textView1);
        ImageView img =(ImageView) row.findViewById(R.id.imageView1);

        //GET AN ITEM FROM ARRAY
        String item=values[position];

        //DYNAMICALLY SET TEXT AND IMAGES DEPENDING ON ITEM IN ARRAY
            tv.setText(item);
            img.setImageResource(R.drawable.baseline_stars_black_24dp);
        return row;
    }

}
