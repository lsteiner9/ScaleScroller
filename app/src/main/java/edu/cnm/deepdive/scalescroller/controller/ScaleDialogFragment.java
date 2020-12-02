package edu.cnm.deepdive.scalescroller.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel;

/**
 * Creates a dialog that pops up at the beginning of each scale level to show the notes of the scale
 * for reference.
 */
public class ScaleDialogFragment extends DialogFragment {

  private AlertDialog dialog;
  private GameViewModel viewModel;
  private Level level;
  private Scale scale;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    viewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
    level = viewModel.getLevel().getValue();
    scale = level.getScale();
    dialog = new AlertDialog.Builder(getActivity())
        .setMessage(getString(R.string.scale_dialog_format,
            scale.getTonic().toString().toUpperCase(),
            scale.getMode().toString().toLowerCase(),
            level.getNotesString()))
        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
        })
        .create();
    return dialog;
  }
}
