package com.apps.peter.weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.peter.weatherapp.DataStructure.HourForecastWeatherData;
import com.apps.peter.weatherapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DayListViewAdapter extends ArrayAdapter<HourForecastWeatherData> {
    //=====================================

    //create class for all the fields in a single row of the listview
    private static class ViewHolder {
        ImageView iv_icon;
        TextView tv_date;
    }

    //=====================================

    //to specify the view type , inflater,and list of users
    private final int VIEW_TYPE_TODAY = 0;
    private final int VIEW_TYPE_OTHERS = 1;
    private LayoutInflater inflater;
    private ArrayList<HourForecastWeatherData> days;


    //=====================================

    //constructor
    public DayListViewAdapter(Context context, int resource, ArrayList<HourForecastWeatherData> items) {
        //Todo change layout here
        super(context, resource, items);
        inflater = LayoutInflater.from(context);
        this.days = items;
    }

    //=====================================
    //get count of users
    public void setListData(ArrayList<HourForecastWeatherData> data) {
       // Log.v("setListData", "Original Size: " + data.size());
        for (HourForecastWeatherData day : data) {
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
            //Log.v("setListData", "Date: " + df2.format(day.getDate()));
            if (!containsDay(day)) {
                days.add(day);
            }
        }
        //Log.v("setListData", "Final Size: " + days.size());
        this.notifyDataSetChanged();
    }

    public boolean containsDay(HourForecastWeatherData day) {
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        for (HourForecastWeatherData day1 : this.days) {
            String dayinListString = df2.format(day1.getDate());
            String dayString = df2.format(day.getDate());
            if (dayinListString.equals(dayString))
                return true;
        }
        return false;
    }

    //=====================================
    //get count of users
    public int getCount() {
        return days.size();
    }

    //=====================================

    //get the user view type
    public int getItemViewType(int position) {
        return (position == 0) ? VIEW_TYPE_TODAY : VIEW_TYPE_OTHERS;
    }

    //=====================================


    public int getViewTypeCount() {
        return 2;
    }

    //=====================================

    //get the user at a certain position
    public HourForecastWeatherData getItem(int position) {
        return days.get(position);
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
        HourForecastWeatherData day = getItem(position);
        ViewHolder vh;
        //=====================================

        if (view == null) {
            vh = new ViewHolder();
            inflater = LayoutInflater.from(getContext());
            //=====================================
            view = inflater.inflate(R.layout.day_list_item, parent, false);
            //=====================================
            vh.iv_icon = view.findViewById(R.id.ic_forward);
            vh.tv_date = view.findViewById(R.id.tv_date);
            //=====================================
            view.setTag(vh);

        }
        //=====================================


        else {
            vh = (ViewHolder) view.getTag();
        }
        //=====================================

        vh.iv_icon.setImageResource(R.drawable.ic_forward_arrow);
        SimpleDateFormat df = new SimpleDateFormat("EEEE");
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String today = df2.format(cal.getTime());
        String date = df2.format(day.getDate());
        if (date.equals(today)) {
            vh.tv_date.setText("Today");
        } else {
            vh.tv_date.setText(df.format(day.getDate()));
        }

        return view;
    }

    //=====================================

}
