package com.hanium.android.mydata.ui.setting;

import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.hanium.android.mydata.R;

import org.json.JSONObject;

import java.util.prefs.Preferences;


public class SettingPreferenceFragment extends PreferenceFragment {

    static String TAG = "SettingPreferenceFragment";

    SharedPreferences prefs;

    Preference logoutPreference;
    ListPreference soundPreference;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preference);
        String userID ;

        logoutPreference = (Preference) findPreference("logout");
        soundPreference = (ListPreference)findPreference("sound_list");
        keywordScreen = (PreferenceScreen)findPreference("keyword_screen");

        logoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("로그아웃하시겠습니까?")
                        .setPositiveButton("확인", null)
                        .setNegativeButton("취소", null)
                        .show();
                return true;
            }
        });

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (!prefs.getString("sound_list", "").equals("")) {
            soundPreference.setSummary(prefs.getString("sound_list", "카톡"));
        }

        if (!prefs.getString("keyword_sound_list", "").equals("")) {
            keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
        }

        if (prefs.getBoolean("keyword", false)) {
            keywordScreen.setSummary("사용");
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);

    }//

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            if (key.equals("sound_list")) {
                soundPreference.setSummary(prefs.getString("sound_list", "카톡"));
            }

            if (key.equals("keyword_sound_list")) {
                keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
            }

            if (key.equals("keyword")){

                if (prefs.getBoolean("keyword", false)) {
                    keywordScreen.setSummary("사용");

                } else {
                    keywordScreen.setSummary("사용안함");
                }

                //2뎁스 PreferenceScreen 내부에서 발생한 환경설정 내용을 2뎁스 PreferenceScreen에 적용하기 위한 소스
                ((BaseAdapter) getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
            }

        }
    };

}
