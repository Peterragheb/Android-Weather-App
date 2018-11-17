package com.apps.peter.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<DayWeather> {
    //=====================================

    //create class for all the fields in a single row of the listview
    private static class ViewHolder {
        ImageView iv_icon;
        TextView tv_date;
        TextView tv_description;
        TextView tv_tempHigh;
        TextView tv_tempLow;
    }

    //=====================================

    //to specify the view type , inflater,and list of users
    private final int VIEW_TYPE_TODAY = 0;
    private final int VIEW_TYPE_OTHERS = 1;
    private LayoutInflater inflater;
    private ArrayList<DayWeather> weatherItems;


    //=====================================

    //constructor
    public ListViewAdapter(Context context, int resource, ArrayList<DayWeather> items) {
        //Todo change layout here
        super(context,resource, items);
        inflater = LayoutInflater.from(context);
        this.weatherItems = items;
    }

    //=====================================

    //get count of users
    public int getCount() {
        return weatherItems.size();
    }

    //=====================================

    //get the user view type
    public int getItemViewType(int position){
        return (position==0)? VIEW_TYPE_TODAY : VIEW_TYPE_OTHERS;
    }

    //=====================================


    public int getViewTypeCount(){
        return 2;
    }

    //=====================================

    //get the user at a certain position
    public DayWeather getItem(int position) {
        return weatherItems.get(position);
    }

    //=====================================

    //get user position
    public long getItemId(int position) {
        return position;
    }

    //=====================================

    //create view
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        DayWeather dayWeather = getItem(position);
        ViewHolder vh;
        //=====================================

        if (view == null) {
            vh = new ViewHolder();
            inflater = LayoutInflater.from(getContext());
            //=====================================
            if (position==0)//if the user is in the first row
                view = inflater.inflate(R.layout.list_first_item, parent, false);
                //=====================================
            else
                view = inflater.inflate(R.layout.list_item, parent, false);
            //=====================================
            vh.iv_icon =  view.findViewById(R.id.iv_icon);
            vh.tv_date =  view.findViewById(R.id.tv_date);
            vh.tv_description =  view.findViewById(R.id.tv_description);
            vh.tv_tempHigh =  view.findViewById(R.id.tv_tempHigh);
            vh.tv_tempLow =  view.findViewById(R.id.tv_tempLow);
            //=====================================
            view.setTag(vh);

        }
        //=====================================


        else {
            vh = (ViewHolder) view.getTag();
        }
        //=====================================

        vh.iv_icon.setImageResource(dayWeather.getIcon());
        if (position== 0)
            vh.tv_date.setText(dayWeather.getDate().toUpperCase()+"'S\nFORECAST");
        else
            vh.tv_date.setText(dayWeather.getDate());
        vh.tv_description.setText(dayWeather.getDescription());
        vh.tv_tempHigh.setText(dayWeather.getTempHigh()+"");
        vh.tv_tempLow.setText(dayWeather.getTempLow()+"");


        return view;
    }

    //=====================================

}
