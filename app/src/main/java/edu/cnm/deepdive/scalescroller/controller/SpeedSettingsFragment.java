package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import edu.cnm.deepdive.scalescroller.R;

/**
 * A preference fragment for the speed of the sidescroller in Learn mode, with a custom style and a
 * button.
 */
public class SpeedSettingsFragment extends PreferenceFragmentCompat {

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
   * Overrides onCreatePreferences to set the preferences from a resource file and to set up a click
   * listener on a back button.
   *
   * @param savedInstanceState A {@code} Bundle.
   * @param rootKey            A String.
   */
  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.speed_preferences, rootKey);
    //noinspection ConstantConditions
    navController = NavHostFragment.findNavController(getParentFragment());
    Preference button = findPreference(getString(R.string.pref_back_button));
    //noinspection ConstantConditions
    button.setOnPreferenceClickListener((v) -> {
      navController.navigate(SpeedSettingsFragmentDirections.openSettings());
      return true;
    });
  }

  /**
   * Overrides onDestroy to set the theme back to the FullscreenTheme used in the rest of the app.
   */
  @Override
  public void onDestroy() {
    //noinspection ConstantConditions
    getContext().getTheme().applyStyle(R.style.FullscreenTheme, true);
    super.onDestroy();
  }
}
