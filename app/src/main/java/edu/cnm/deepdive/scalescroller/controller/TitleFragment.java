package edu.cnm.deepdive.scalescroller.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.FragmentTitleBinding;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;

/**
 * The title fragment is the main navigational hub, and allows the user to select different game
 * modes, go to settings, or logout.
 */
public class TitleFragment extends Fragment {

  private FragmentTitleBinding binding;
  private GoogleSignInService signInService;
  private NavController navController;


  /**
   * Initializes display text and navigation.
   *
   * @param inflater           A {@code LayoutInflater}.
   * @param container          A {@code ViewGroup}.
   * @param savedInstanceState A {@code Bundle}.
   * @return A {@code View}.
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    signInService = GoogleSignInService.getInstance();
    binding = FragmentTitleBinding.inflate(getLayoutInflater());
    binding.playingAs.setText(getString(R.string.playing_as,
        signInService.getAccount().getDisplayName()));
    binding.logoutButton.setOnClickListener((v) -> logout());
    navController = NavHostFragment.findNavController(this);
    binding.learnButton.setOnClickListener((v) ->
        navController.navigate(TitleFragmentDirections.openScaleSelect()));
    binding.challengeButton.setOnClickListener((v) ->
        navController.navigate(TitleFragmentDirections.openChallengeModeGame()));
    binding.settingsButton.setOnClickListener(
        (v) -> navController.navigate(TitleFragmentDirections.openSettings()));
    return binding.getRoot();
  }

  private void logout() {
    GoogleSignInService.getInstance().signOut()
        .addOnCompleteListener((ignored) -> {
          Intent intent = new Intent(getActivity(), LoginActivity.class)
              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }
}