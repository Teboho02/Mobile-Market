package com.example.mobilemarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

public class CustomBaseAdapter extends BaseAdapter {
    Context con;
    String[] name;
    LayoutInflater layoutInflater;
    ImageView v ;
    String[] description;
    String[] prize;
    String[] Imageurl;
    public CustomBaseAdapter() {
    }

    public CustomBaseAdapter (Context context, String[] name, String[] description, String[] price, String[] Imageurl){
        this.con = context;
        this.name = name;
        layoutInflater = LayoutInflater.from(context);
        this.v = v;
        this.description = description;
        this.prize = price;
        this.Imageurl = Imageurl;

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
        TextView price = (TextView) x.findViewById(R.id.homePrize);
        price.setText(prize[position]);
        TextView desc = (TextView) x.findViewById(R.id.HomeDescpription);
        desc.setText(description[position]);
        String link = (Imageurl[position]);

      //  ImageView b = (ImageView) x.findViewById(R.id.)
        try{
            Picasso.get().load(link).into((ImageView) x.findViewById(R.id.imagePosted));
        //    ImageLoader.getInstance().displayImage("https://lamp.ms.wits.ac.za/~s2446577/image/c.jpg", v);

        }
        catch (Exception e){

        }

        return x;
    }
}
