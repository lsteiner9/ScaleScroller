package edu.cnm.deepdive.scalescroller.controller;

import android.annotation.SuppressLint;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.FragmentTitleBinding;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;

public class TitleFragment extends Fragment {

  FragmentTitleBinding binding;
  private GoogleSignInService signInService;
  private NavController navController;


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
    // TODO how to pass arguments here? Pass in a bundle?
    binding.challengeButton.setOnClickListener((v) ->
        navController.navigate(TitleFragmentDirections.openChallengeModeGame()));
    binding.settingsButton.setOnClickListener(
        (v) -> navController.navigate(TitleFragmentDirections.openSettings()));    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
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