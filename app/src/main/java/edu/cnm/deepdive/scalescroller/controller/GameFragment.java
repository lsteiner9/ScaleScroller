package edu.cnm.deepdive.scalescroller.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.scalescroller.databinding.FragmentGameBinding;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;

public class GameFragment extends Fragment {

  private FragmentGameBinding binding;

  public static GameFragment createInstance() {
    GameFragment fragment = new GameFragment();
    Bundle args = new Bundle();
    //Add parameter values to args, using args.put???().
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    // Do whatever is necessary with args.
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentGameBinding.inflate(inflater);
    if (savedInstanceState != null) {
      Note tonic = GameFragmentArgs.fromBundle(getArguments()).getTonic();
      Mode mode = GameFragmentArgs.fromBundle(getArguments()).getMode();
      GameMode gameMode = GameFragmentArgs.fromBundle(getArguments()).getGameMode();
    }
    binding.pauseButton.setOnClickListener((v) -> {
      // TODO popup a dialog with volume toggle, resume, return to title screen buttons
      Intent intent = new Intent(getActivity(), TitleActivity.class)
          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
    return binding.getRoot();
  }

  public enum GameMode {
    LEARN, CHALLENGE
  }

}