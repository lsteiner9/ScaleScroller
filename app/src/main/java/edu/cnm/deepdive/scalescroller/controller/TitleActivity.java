package edu.cnm.deepdive.scalescroller.controller;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.ActivityTitleBinding;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;

public class TitleActivity extends AppCompatActivity {

  private ActivityTitleBinding binding;
  GoogleSignInService signInService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    signInService = GoogleSignInService.getInstance();
    binding = ActivityTitleBinding.inflate(getLayoutInflater());
    binding.playingAs.setText(getString(R.string.playing_as,
        signInService.getAccount().getDisplayName()));
    binding.logoutButton.setOnClickListener((v) -> logout());
    // TODO can't pass fragments as intents, what instead?
    binding.learnButton.setOnClickListener((v) -> {
      getSupportFragmentManager().beginTransaction()
          .setReorderingAllowed(true)
          .add(R.id.fragment_container_view, ScaleSelectFragment.class, null)
          .commit();
//      Intent intent = new Intent(this, ScaleSelectFragment.class)
//          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//      startActivity(intent);
      // TODO how to pass arguments here? Pass in a bundle?
    });
    binding.challengeButton.setOnClickListener((v) -> {
      getSupportFragmentManager().beginTransaction()
          .setReorderingAllowed(true)
          .add(R.id.fragment_container_view, GameFragment.class, null)
          .commit();
//      Intent intent = new Intent(this, GameFragment.class)
//          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//      startActivity(intent);
    });
    binding.settingsButton.setOnClickListener((v) -> {
      getSupportFragmentManager().beginTransaction()
          .setReorderingAllowed(true)
          .add(R.id.fragment_container_view, SettingsFragment.class, null)
          .commit();
//      Intent intent = new Intent(this, SettingsFragment.class)
//          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//      startActivity(intent);
    });
    setContentView(binding.getRoot());
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
  }

  private void logout() {
    GoogleSignInService.getInstance().signOut()
        .addOnCompleteListener((ignored) -> {
          Intent intent = new Intent(this, LoginActivity.class)
              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }
}