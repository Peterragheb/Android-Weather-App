package com.apps.peter.weatherapp.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.peter.weatherapp.Fragment.ContactUsFragment;
import com.apps.peter.weatherapp.Fragment.DayWeatherForecastFragment;
import com.apps.peter.weatherapp.Fragment.ForecastFragment;
import com.apps.peter.weatherapp.Fragment.HomeFragment;
import com.apps.peter.weatherapp.Listener.FragmentInteractionListener;
import com.apps.peter.weatherapp.R;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener, View.OnClickListener {
    private HomeFragment homeFragment;
    private ForecastFragment forecastFragment;
    private ContactUsFragment contactUsFragment;
    private DayWeatherForecastFragment dayWeatherForecastFragment;
    private FragmentManager fragmentManager;
    private ImageView iv_back, iv_about;
    private Animation anim_scale_up, anim_scale_down;
    private TextView tv_cityName, tv_appName;
    private int transactionState;// 0 => no transaction done        1 => from home to forecast      2 => from home to about          3 => from forecast to about        4 => from forecast to dayWeather
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIComponents();
        initFragment(savedInstanceState);

    }

    private void initUIComponents() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_about = findViewById(R.id.iv_about);
        iv_about.setOnClickListener(this);
        tv_cityName = findViewById(R.id.tv_cityName);
        tv_appName = findViewById(R.id.tv_appName);
        anim_scale_up = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        anim_scale_down = AnimationUtils.loadAnimation(this, R.anim.scale_down);
    }

    private void initFragment(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            transactionState = 0; // no fragment transaction done
            homeFragment = new HomeFragment();
            homeFragment.setInteractionListener(this);
            fragmentTransaction.add(R.id.ll_container, homeFragment, "HomeFragment");
            fragmentTransaction.commit();
        } else {
            transactionState = savedInstanceState.getInt("transactionState", 0);
            cityName = savedInstanceState.getString("cityName");
            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag("HomeFragment");
            if (homeFragment != null)
                homeFragment.setInteractionListener(this);
            forecastFragment = (ForecastFragment) fragmentManager.findFragmentByTag("ForecastFragment");
            if (forecastFragment != null)
                forecastFragment.setInteractionListener(this);
            if (transactionState == 1) {
                if (forecastFragment != null) {
                    forecastFragment.setInteractionListener(this);
                    iv_back.startAnimation(anim_scale_up);
                    iv_back.setVisibility(View.VISIBLE);
                    tv_cityName.setText(cityName);
                    tv_cityName.startAnimation(anim_scale_up);
                    tv_appName.setVisibility(View.INVISIBLE);
                    tv_cityName.setVisibility(View.VISIBLE);
                }
            } else if (transactionState == 2 || transactionState == 3) {
                contactUsFragment = (ContactUsFragment) fragmentManager.findFragmentByTag("ContactUsFragment");
                if (contactUsFragment != null) {
                    iv_back.startAnimation(anim_scale_up);
                    iv_back.setVisibility(View.VISIBLE);
                    tv_cityName.startAnimation(anim_scale_down);
                    tv_appName.setVisibility(View.VISIBLE);
                    tv_cityName.setVisibility(View.INVISIBLE);
                    iv_about.startAnimation(anim_scale_down);
                    iv_about.setVisibility(View.INVISIBLE);
                }
            } else if (transactionState == 4) {
                dayWeatherForecastFragment = (DayWeatherForecastFragment) fragmentManager.findFragmentByTag("DayWeatherForecastFragment");
                if (dayWeatherForecastFragment != null) {
                    iv_back.startAnimation(anim_scale_up);
                    iv_back.setVisibility(View.VISIBLE);
                    tv_cityName.setText(cityName);
                    tv_cityName.startAnimation(anim_scale_up);
                    tv_appName.setVisibility(View.INVISIBLE);
                    tv_cityName.setVisibility(View.VISIBLE);
                    iv_about.startAnimation(anim_scale_down);
                    iv_about.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void gotoForecastFragment(int transactionState, String cityName) {
        this.transactionState = transactionState;
        this.cityName = cityName;
        iv_back.startAnimation(anim_scale_up);
        iv_back.setVisibility(View.VISIBLE);
        tv_cityName.setText(cityName);
        tv_cityName.startAnimation(anim_scale_up);
        tv_appName.setVisibility(View.INVISIBLE);
        tv_cityName.setVisibility(View.VISIBLE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (forecastFragment == null) {
            forecastFragment = new ForecastFragment();
            forecastFragment.setInteractionListener(this);
        }
        forecastFragment.setcityName(cityName);
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.ll_container, forecastFragment, "ForecastFragment");
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }

    @Override
    public void gotoHomeFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            homeFragment.setInteractionListener(this);
        }
        fragmentTransaction.replace(R.id.ll_container, homeFragment, "HomeFragment");
        fragmentTransaction.commit();
        iv_back.startAnimation(anim_scale_down);
        iv_back.setVisibility(View.INVISIBLE);
        tv_appName.setVisibility(View.VISIBLE);
        tv_cityName.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gotoAboutFragment(int transactionState) {
        this.transactionState = transactionState;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        iv_back.startAnimation(anim_scale_up);
        iv_back.setVisibility(View.VISIBLE);
        if (transactionState == 3) {
            tv_cityName.startAnimation(anim_scale_down);
            tv_cityName.setVisibility(View.INVISIBLE);
        }
        tv_appName.setVisibility(View.VISIBLE);
        iv_about.startAnimation(anim_scale_down);
        iv_about.setVisibility(View.INVISIBLE);
        if (contactUsFragment == null) {
            contactUsFragment = new ContactUsFragment();
        }
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.ll_container, contactUsFragment, "ContactUsFragment");
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }

    @Override
    public void gotoDailyForecastFragment(int transactionState, String cityName, int position) {
        this.transactionState = transactionState;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        iv_back.startAnimation(anim_scale_up);
        iv_back.setVisibility(View.VISIBLE);
        iv_about.startAnimation(anim_scale_down);
        iv_about.setVisibility(View.INVISIBLE);
        if (dayWeatherForecastFragment == null) {
            dayWeatherForecastFragment = new DayWeatherForecastFragment();
        }
        dayWeatherForecastFragment.setProperties(cityName, position);
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.ll_container, dayWeatherForecastFragment, "DayWeatherForecastFragment");
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.v("BACKSTACKSIZE", "size: " + fragmentManager.getBackStackEntryCount());
        if (fragmentManager.getBackStackEntryCount() > 0) {
            if (transactionState == 1 || transactionState == 2) { // in case of navigating back to homefragment
                fragmentManager.popBackStack();
                iv_back.startAnimation(anim_scale_down);
                iv_back.setVisibility(View.INVISIBLE);
                tv_appName.setVisibility(View.VISIBLE);
                tv_cityName.setVisibility(View.INVISIBLE);
                if (transactionState == 2) {
                    iv_about.startAnimation(anim_scale_up);
                    iv_about.setVisibility(View.VISIBLE);
                }
                transactionState = 0;
            } else if (transactionState == 3) {// in case of navigating back to forecastfragment from aboutfragment
                fragmentManager.popBackStack();
                tv_cityName.setText(cityName);
                tv_cityName.startAnimation(anim_scale_up);
                tv_appName.setVisibility(View.INVISIBLE);
                tv_cityName.setVisibility(View.VISIBLE);
                iv_about.startAnimation(anim_scale_up);
                iv_about.setVisibility(View.VISIBLE);
                transactionState = 1;
            } else if (transactionState == 4) {// in case of navigating back to forecastfragment from dayweatherforecastfragment
                fragmentManager.popBackStack();
                tv_cityName.setText(cityName);
                iv_about.startAnimation(anim_scale_up);
                iv_about.setVisibility(View.VISIBLE);
                transactionState = 1;
            }

        }
        if (fragmentManager.getBackStackEntryCount() == 0) {
            finish();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("cityName", cityName);
        outState.putInt("transactionState", transactionState);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            if (transactionState == 1 || transactionState == 2) {// in case of navigating back to homefragment
                fragmentManager.popBackStack();
                iv_back.startAnimation(anim_scale_down);
                iv_back.setVisibility(View.INVISIBLE);
                tv_appName.setVisibility(View.VISIBLE);
                tv_cityName.setVisibility(View.INVISIBLE);
                if (transactionState == 2) {
                    iv_about.startAnimation(anim_scale_up);
                    iv_about.setVisibility(View.VISIBLE);
                }
                transactionState = 0;
            } else if (transactionState == 3) {// in case of navigating back to forecastfragment from aboutfragment
                fragmentManager.popBackStack();
                tv_cityName.setText(cityName);
                tv_cityName.startAnimation(anim_scale_up);
                tv_appName.setVisibility(View.INVISIBLE);
                tv_cityName.setVisibility(View.VISIBLE);
                iv_about.startAnimation(anim_scale_up);
                iv_about.setVisibility(View.VISIBLE);
                transactionState = 1;
            } else if (transactionState == 4) {// in case of navigating back to forecastfragment from dayweatherforecastfragment
                fragmentManager.popBackStack();
                tv_cityName.setText(cityName);
                iv_about.startAnimation(anim_scale_up);
                iv_about.setVisibility(View.VISIBLE);
                transactionState = 1;
            }
        }
        if (view.getId() == R.id.iv_about) {
            if (transactionState == 0) {
                gotoAboutFragment(2);
            } else if (transactionState == 1) {
                gotoAboutFragment(3);
            }
        }
    }
}
