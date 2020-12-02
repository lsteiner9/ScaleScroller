package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import edu.cnm.deepdive.scalescroller.R;

/**
 * A preference fragment for audio settings, with a custom style and a button.
 */
public class AudioSettingsFragment extends PreferenceFragmentCompat {

  private NavController navController;

  /**
   * Overrides onCreate to apply a preferences-specific style.
   *
   * @param savedInstanceState A {@code} Bundle.
   */
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //noinspection ConstantConditions
    getContext().getTheme().applyStyle(R.style.FullscreenTheme_Preferences, true);
  }

  /**
   * Overrides onCreatePreferences to set the preferences from a resource file and to set up a
   * click listener on a back button.
   *
   * @param savedInstanceState A {@code} Bundle.
   * @param rootKey A String.
   */
  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.audio_preferences, rootKey);
    //noinspection ConstantConditions
    navController = NavHostFragment.findNavController(getParentFragment());
    Preference button = findPreference(getString(R.string.pref_back_button));
    //noinspection ConstantConditions
    button.setOnPreferenceClickListener((v) -> {
      navController.navigate(AudioSettingsFragmentDirections.openSettings());
      return true;
    });
  }

}
