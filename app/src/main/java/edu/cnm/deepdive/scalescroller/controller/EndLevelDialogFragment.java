package edu.cnm.deepdive.scalescroller.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.controller.GameFragment.GameMode;

/**
 * Creates a dialog that pops up at the end of the level (in Learn mode) or game (in Challenge
 * mode). Informs the player of the game status - won or lost for Learn mode, score for Challenge
 * mode.
 */
public class EndLevelDialogFragment extends DialogFragment {

  private AlertDialog dialog;
  private String gameMode;
  private boolean gameWon;
  private int score;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    gameWon = EndLevelDialogFragmentArgs.fromBundle(getArguments()).getGameWon();
    gameMode = EndLevelDialogFragmentArgs.fromBundle(getArguments()).getGameMode();
    score = EndLevelDialogFragmentArgs.fromBundle(getArguments()).getScore();
    String format = (gameWon)? getString(R.string.congratulations_learn) : getString(R.string.failure_learn);
    format = (gameMode.equals(GameMode.LEARN.toString()))? format : getString(R.string.congratulations_challenge);
    dialog = new AlertDialog.Builder(getActivity())
        .setMessage(String.format(format, score))
        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
        })
        .create();
    return dialog;
  }
}
