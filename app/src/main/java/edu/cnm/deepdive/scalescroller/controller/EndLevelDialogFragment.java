package edu.cnm.deepdive.scalescroller.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.controller.GameFragment.GameMode;
import edu.cnm.deepdive.scalescroller.viewmodel.MainViewModel;

public class EndLevelDialogFragment extends DialogFragment {

  private AlertDialog dialog;
  private MainViewModel viewModel;
  private GameMode gameMode;
  private boolean levelWon;
  private int score;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    //noinspection ConstantConditions
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    levelWon = viewModel.getLevelWon().getValue();
    score = viewModel.getLevel().getValue().getScore();
    gameMode = viewModel.getGameMode();
    String format = (levelWon)? getString(R.string.congratulations_learn) : getString(R.string.failure_learn);
    format = (gameMode == GameMode.LEARN)? format : getString(R.string.congratulations_challenge);
    dialog = new AlertDialog.Builder(getActivity())
        .setMessage(String.format(format, score))
        .setPositiveButton(android.R.string.ok, (dialog, which) -> {})
        .create();
    return dialog;
  }
}
