package edu.cnm.deepdive.scalescroller.controller;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel;

/**
 * Creates a dialog that pops up when the player presses the pause button. From this dialog, the
 * player can resume the game or return to the title screen.
 */
public class PauseDialogFragment extends DialogFragment {

  private AlertDialog dialog;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    dialog = new Builder(getContext())
        .setMessage(R.string.level_paused)
        .setPositiveButton(R.string.resume, (dialog, which) -> onClose(true))
        .setNegativeButton(R.string.return_to_title, (dialog, which) -> onClose(false))
        .create();
    return dialog;
  }

  private void onClose(boolean resume) {
    //noinspection ConstantConditions
    new ViewModelProvider(getActivity()).get(GameViewModel.class).setResume(resume);
  }

}
