package com.example.moviecatalogue.alarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.MovieResponse;
import com.example.moviecatalogue.model.ResultsItem;
import com.example.moviecatalogue.service.ServiceApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.moviecatalogue.service.ServiceApi.BASE_URL;

public class MySettingPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private RequestQueue mRequestQueue;

    List<ResultsItem> notifList;
    List<ResultsItem> notif = new ArrayList<>();
    //string
    private String REMINDER;
    private String RELEASE;
    //swith button
    private SwitchPreference reminder,release;

    private ReleaseMovieAlarm releaseMovieAlarm = new ReleaseMovieAlarm();
    private DailyAlarm dailyAlarm;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_setting);
        init();
        Preference pref = findPreference(getString(R.string.key_language));
        if (pref != null) {
            pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                    return true;
                }
            });
        }
        setSummaries();
    }

    private void init() {
        REMINDER = getResources().getString(R.string.key_reminder);
        RELEASE = getResources().getString(R.string.key_release);

        release = findPreference(RELEASE);
        reminder = findPreference(REMINDER);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        dailyAlarm = new DailyAlarm();
        notifList = new ArrayList<>();
    }

    private void setSummaries() {
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();
        release.setChecked(sh.getBoolean(RELEASE, false));
        reminder.setChecked(sh.getBoolean(REMINDER, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        boolean check = sharedPreferences.getBoolean(RELEASE, false);
        boolean checkDaily = sharedPreferences.getBoolean(REMINDER, false);
        if (key.equals(RELEASE)) {
            release.setChecked(sharedPreferences.getBoolean(RELEASE, false));
            if (check) {
                getData();
            } else {
                releaseMovieAlarm.cancelAlarm(getActivity());
            }
        } else if (key.equals(REMINDER)) {
            reminder.setChecked(sharedPreferences.getBoolean(REMINDER, false));
            if (checkDaily) {
//                String time = "17:39";
//                String message = "missing you";
                dailyAlarm.setAlarm(getActivity());
//                Toast.makeText(getActivity(), "jam " + time, Toast.LENGTH_SHORT).show();
            } else {
                dailyAlarm.cancelAlarm(getActivity());
            }

        }



    }

    //get data
    public void getData() {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        String today;
        if (currentMonth <= 9 && currentDay <= 9){
            today = currentYear+"-0"+currentMonth+"-0"+currentDay;
        } else if (currentMonth <= 9) {
            today = currentYear+"-0"+currentMonth+"-"+currentDay;
        } else if (currentDay <= 9) {
            today = currentYear+"-"+currentMonth+"-0"+currentDay;
        } else {
            today = currentYear+"-"+currentMonth+"-"+currentDay;
        }
        Log.d("today","today is "+today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        final String currentDate = sdf.format(date);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        Call<MovieResponse> resultsItemCall = serviceApi.getRelease(today,today);
        resultsItemCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, retrofit2.Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List<ResultsItem> listToday = new ArrayList<>();
                    notif = response.body().getMovieResults();
                    for (int i = 0;i < notif.size(); i++) {
                        ResultsItem resultsItem = notif.get(i);
                        Date movieDate = dateFormatter(resultsItem.getReleaseDate());
                        Log.d("response","onResponse :"+movieDate);

                        if (movieDate.equals(dateFormatter(currentDate))) {
                            listToday.add(resultsItem);
                        }
                    }
                    Log.d("response","onRespone :"+listToday.size());
                    releaseMovieAlarm.setAlarm(getActivity(), listToday);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


    private Date dateFormatter(String movieDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = sdf.parse(movieDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

}
