package edu.cnm.deepdive.scalescroller.controller;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.FragmentPauseDialogBinding;

//TODO why do none of the dialogs work????
/**
 * Creates a dialog that pops up when the player presses the pause button. From this dialog, the
 * player can resume the game or return to the title screen.
 */
public class PauseDialogFragment extends DialogFragment {

  //  private AlertDialog dialog;
//  private NavController navController;
//
//  @NonNull
//  @Override
//  public Dialog onCreateDialog(
//      @Nullable Bundle savedInstanceState) {
//    navController = NavHostFragment.findNavController(this);
//    dialog = new Builder(getContext())
//        .setMessage(R.string.level_paused)
//        .setPositiveButton(R.string.resume, (dialog, which) -> {})
//        .setNegativeButton(R.string.return_to_title, ((dialog, which)
//            -> navController.navigate(PauseDialogFragmentDirections.openTitle())))
//        .create();
//    dialog.show();
//    return dialog;
//  }
  private AlertDialog dialog;
  private NavController navController;
  private FragmentPauseDialogBinding binding;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    navController = NavHostFragment.findNavController(this);
    binding = FragmentPauseDialogBinding.inflate(LayoutInflater.from(getContext()));
    binding.returnButton.setOnClickListener((v) -> {
      navController.navigate(PauseDialogFragmentDirections.openTitle());
    });
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    dialog = new Builder(getContext())
        .setView(binding.getRoot())
        .setMessage(R.string.level_paused)
        .setPositiveButton(R.string.resume, (dialog, which) -> {
        })
        .setNegativeButton(R.string.return_to_title, ((dialog, which)
            -> navController.navigate(PauseDialogFragmentDirections.openTitle())))
        .create();
    dialog.show();
    return dialog;
  }

  //this override is needed to make the dialog get created
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return binding.getRoot();
  }
}
