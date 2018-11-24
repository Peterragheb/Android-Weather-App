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

public class HourListViewAdapter extends ArrayAdapter<HourForecastWeatherData> {

    //=====================================

    //create class for all the fields in a single row of the listview
    private static class ViewHolder {
        ImageView iv_icon;
        TextView tv_date;
        TextView tv_description;
        TextView tv_temp;
    }

    //=====================================

    //to specify the view type , inflater,and list of users
    private final int VIEW_TYPE_TODAY = 0;
    private final int VIEW_TYPE_OTHERS = 1;
    private LayoutInflater inflater;
    private ArrayList<HourForecastWeatherData> weatherItems;


    //=====================================

    //constructor
    public HourListViewAdapter(Context context, int resource, ArrayList<HourForecastWeatherData> items) {
        //Todo change layout here
        super(context, resource, items);
        inflater = LayoutInflater.from(context);
        this.weatherItems = items;
    }

    //=====================================
    public void setListData(ArrayList<HourForecastWeatherData> hourForecastWeatherData, int position) {
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<ArrayList<HourForecastWeatherData>> daysList = new ArrayList<>();
        String lastdate = "";
        ArrayList<HourForecastWeatherData> templist = new ArrayList<>();
        for (int i = 0; i < hourForecastWeatherData.size(); i++) {
            String dayinListString = df2.format(hourForecastWeatherData.get(i).getDate());
            if (lastdate.equals(dayinListString)) {
                templist.add(hourForecastWeatherData.get(i));
                if (i == hourForecastWeatherData.size() - 1) {
                    daysList.add((ArrayList<HourForecastWeatherData>) templist.clone());
                }
            } else {
                if (i == 0)
                    templist.add(hourForecastWeatherData.get(i));
                else if (i == hourForecastWeatherData.size() - 1) {
                    daysList.add((ArrayList<HourForecastWeatherData>) templist.clone());
                    templist = new ArrayList<>();
                    templist.add(hourForecastWeatherData.get(i));
                    daysList.add((ArrayList<HourForecastWeatherData>) templist.clone());
                } else {
                    daysList.add((ArrayList<HourForecastWeatherData>) templist.clone());
                    templist = new ArrayList<>();
                    templist.add(hourForecastWeatherData.get(i));
                }

            }
            lastdate = dayinListString;
        }
        this.weatherItems = daysList.get(position);
        this.notifyDataSetChanged();
    }
    //=====================================

    //get count of users
    public int getCount() {
        return weatherItems.size();
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
        HourForecastWeatherData dayWeather = getItem(position);
        ViewHolder vh;
        //=====================================

        if (view == null) {
            vh = new ViewHolder();
            inflater = LayoutInflater.from(getContext());
            //=====================================
            view = inflater.inflate(R.layout.hour_list_item, parent, false);
            //=====================================
            vh.iv_icon = view.findViewById(R.id.iv_icon);
            vh.tv_date = view.findViewById(R.id.tv_timeHour);
            vh.tv_description = view.findViewById(R.id.tv_description);
            vh.tv_temp = view.findViewById(R.id.tv_temp);
            //=====================================
            view.setTag(vh);

        }
        //=====================================


        else {
            vh = (ViewHolder) view.getTag();
        }
        //=====================================

        vh.iv_icon.setImageResource(dayWeather.getIcon());
        SimpleDateFormat df = new SimpleDateFormat("h a");
        vh.tv_date.setText(df.format(dayWeather.getDate()));
        vh.tv_description.setText(dayWeather.getDescription());
        vh.tv_temp.setText(dayWeather.getTemperature() + "");


        return view;
    }

    //=====================================

}
