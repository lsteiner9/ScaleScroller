package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import edu.cnm.deepdive.scalescroller.R;

public class AudioSettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
      setPreferencesFromResource(R.xml.audio_preferences, rootKey);
//      setPreferencesFromResource(R.xml.speed_preferences, rootKey);
  }
}
