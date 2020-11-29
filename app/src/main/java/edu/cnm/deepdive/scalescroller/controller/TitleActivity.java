package edu.cnm.deepdive.scalescroller.controller;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.MobileNavigationDirections;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.ActivityTitleBinding;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;

public class TitleActivity extends AppCompatActivity {

  private ActivityTitleBinding binding;
  private GoogleSignInService signInService;
  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    signInService = GoogleSignInService.getInstance();
    binding = ActivityTitleBinding.inflate(getLayoutInflater());
    binding.playingAs.setText(getString(R.string.playing_as,
        signInService.getAccount().getDisplayName()));
    binding.logoutButton.setOnClickListener((v) -> logout());
    setContentView(binding.getRoot());
    navController = ((NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.fragment_container_view)).getNavController();
    binding.learnButton.setOnClickListener((v) ->
        navController.navigate(MobileNavigationDirections.openScaleSelect()));
    // TODO how to pass arguments here? Pass in a bundle?
    binding.challengeButton.setOnClickListener((v) ->
        navController.navigate(MobileNavigationDirections.openChallengeModeGame()));
    binding.settingsButton.setOnClickListener(
        (v) -> navController.navigate(MobileNavigationDirections.openSettings()));
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