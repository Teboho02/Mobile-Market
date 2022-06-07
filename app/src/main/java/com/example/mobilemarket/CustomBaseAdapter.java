package com.example.mobilemarket;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

public class CustomBaseAdapter extends BaseAdapter {
    Context con;
    String[] name;
    LayoutInflater layoutInflater;
    String[] description;
    String[] prize;
    String[] aveRating;
    Bitmap[] bit;
    String[] product_name;
    String[] date;
    public CustomBaseAdapter() {
    }

    public CustomBaseAdapter (Context context, String[] name, String[] description, String[] prize,String [] aveRating,Bitmap[] bit,String[] product_name,String[] date){
        this.con = context;
        layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.description = description;
        this.prize = prize;
        this.product_name = product_name;
        this.aveRating = aveRating;
        this.bit = bit;
        this.date = date;
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View x, ViewGroup viewGroup) {
            x = layoutInflater.inflate(R.layout.activity_custom_list_view,null);
            TextView naam = (TextView) x.findViewById(R.id.homeName);
            naam.setText(name[position]);
            Toast.makeText(con, ""+name[position], Toast.LENGTH_SHORT).show();
            TextView price = (TextView) x.findViewById(R.id.homePrize);
            price.setText(prize[position]);
            TextView desc = (TextView) x.findViewById(R.id.HomeDescpription);
           // desc.setText(description[position]);
            desc.setText("");
            TextView aveRati = (TextView) x.findViewById(R.id.HomeaverageRating);
            aveRati.setText("No reviews yet");
            ImageView ima = (ImageView) x.findViewById(R.id.imagePosted);
            ima.setImageBitmap(bit[position]);
            TextView productname = x.findViewById(R.id.product_name);
            productname.setText(product_name[position]);
            TextView da = x.findViewById(R.id.da);
            da.setText(date[position]);
  //          System.out.println("Happemed");


        return x;
    }

}
