package edu.cnm.deepdive.scalescroller.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class PauseDialogFragment extends DialogFragment {

  private AlertDialog dialog;
  private NavController navController;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    navController = NavHostFragment.findNavController(this);
    dialog = new AlertDialog.Builder(getActivity())
        .setMessage("Level paused")
        .setPositiveButton("Resume", (dialog, which) -> {})
        .setNegativeButton("Return to title", ((dialog, which)
            -> navController.navigate(PauseDialogFragmentDirections.openTitle())))
        .create();
    return dialog;
  }
}
