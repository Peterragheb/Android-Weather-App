package com.apps.peter.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactUsFragment extends Fragment implements View.OnClickListener {
    private TextView tv_openweatherURL;
    private TextView tv_developerEmail;
    private TextView tv_developerPhone;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contactus,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_openweatherURL = getActivity().findViewById(R.id.tv_openweatherURL);
        tv_developerEmail = getActivity().findViewById(R.id.tv_developerEmail);
        tv_developerPhone = getActivity().findViewById(R.id.tv_developerPhone);
        tv_openweatherURL.setOnClickListener(this);
        tv_developerEmail.setOnClickListener(this);
        tv_developerPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_openweatherURL){
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            webIntent.setData(Uri.parse(getString(R.string.openweatherURL)));
            startActivity(Intent.createChooser(webIntent, "Open openweather Website" ));
        }else if (view.getId() == R.id.tv_developerEmail){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",getString(R.string.developerEmail), null));
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }else if (view.getId() == R.id.tv_developerPhone){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+getString(R.string.developerPhone)));
            startActivity(Intent.createChooser(intent, "Call Developer"));
        }
    }
}
