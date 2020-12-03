package edu.cnm.deepdive.scalescroller.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel;

/**
 * Creates a dialog that pops up at the beginning of each scale level to show the notes of the scale
 * for reference.
 */
public class ScaleDialogFragment extends DialogFragment {

  private AlertDialog dialog;

  /**
   * Sets the style for the dialog.
   *
   * @param savedInstanceState A {@code Bundle}
   */
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);
  }

  /**
   * Creates and sets the text for the dialog.
   *
   * @param savedInstanceState A {@code Bundle}
   * @return The dialog that was created.
   */
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    //noinspection ConstantConditions
    String tonic = ScaleDialogFragmentArgs.fromBundle(getArguments()).getTonic();
    String mode = ScaleDialogFragmentArgs.fromBundle(getArguments()).getMode();
    String notes = ScaleDialogFragmentArgs.fromBundle(getArguments()).getNotes();
    dialog = new AlertDialog.Builder(getActivity())
        .setMessage(getString(R.string.scale_dialog_format, tonic, mode, notes))
        .setPositiveButton(android.R.string.ok, (dialog, which) -> onClose())
        .create();
    return dialog;
  }

  private void onClose() {
    //noinspection ConstantConditions
    new ViewModelProvider(getActivity()).get(GameViewModel.class).setPaused(false);
  }
}
