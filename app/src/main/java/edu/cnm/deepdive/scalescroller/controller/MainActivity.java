package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.scalescroller.databinding.ActivityMainBinding;

/**
 * The main activity hosts all of the fragments needed for navigation.
 */
public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  /**
   * Inflates the xml file and sets the content view.
   *
   * @param savedInstanceState A {@code Bundle}
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  }

}