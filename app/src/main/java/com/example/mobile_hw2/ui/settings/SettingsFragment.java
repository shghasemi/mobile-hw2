package com.example.mobile_hw2.ui.settings;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.mobile_hw2.R;


public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        Preference deleteData = findPreference("delete_data");
        deleteData.setOnPreferenceClickListener(preference -> {
            deleteData();
            return true;
        });

        Preference darkMode = findPreference("dark_mode");
        darkMode.setOnPreferenceChangeListener((preference, newValue) -> {
            switch_dark_mode((Boolean) newValue);
            return true;
        });
    }

    private void switch_dark_mode(boolean darkMode) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void deleteData() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.delete_data_title)
                .setMessage(R.string.delete_data_confirm) //.setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", (dialog, whichButton) -> {
                    // TODO: clear data
                    Toast.makeText(getContext(),"Deleted", Toast.LENGTH_SHORT).show();;
                })
                .setNegativeButton("No", null).show();
    }
}