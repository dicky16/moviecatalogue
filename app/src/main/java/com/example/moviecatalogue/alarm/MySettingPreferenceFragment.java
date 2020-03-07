package com.example.moviecatalogue.alarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MySettingPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    List<ResultsItem> notif = new ArrayList<>();
    //string
    private String REMINDER;
    private String RELEASE;
    //swith button
    private SwitchPreference reminder,release;
    private ReleaseAlarm releaseAlarm;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_setting);
        init();
        String language = Locale.getDefault().getDisplayLanguage();
        Preference pref = findPreference(getString(R.string.key_language));
        pref.setSummary(language);
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
        releaseAlarm = new ReleaseAlarm(getContext());
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
                releaseAlarm.setReleaseTodayReminder();
            } else {
//                releaseMovieAlarm.cancelAlarm(getActivity());
                releaseAlarm.cancelReleaseToday(getContext());
            }
        } else if (key.equals(REMINDER)) {
            reminder.setChecked(sharedPreferences.getBoolean(REMINDER, false));
            if (checkDaily) {
                releaseAlarm.setDailyReminder();
            } else {
                releaseAlarm.cancelDailyReminder(getContext());
            }

        }



    }

}
