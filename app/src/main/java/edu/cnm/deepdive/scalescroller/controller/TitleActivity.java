package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.scalescroller.databinding.ActivityTitleBinding;

public class TitleActivity extends AppCompatActivity {

  private ActivityTitleBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityTitleBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
  }

}