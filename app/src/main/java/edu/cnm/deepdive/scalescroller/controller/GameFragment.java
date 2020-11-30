package edu.cnm.deepdive.scalescroller.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.FragmentGameBinding;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.LearnLevelAttemptRepository;

public class GameFragment extends Fragment {

  private LearnLevelAttemptRepository learnLevelAttemptRepository;
  private ChallengeAttemptRepository challengeAttemptRepository;
  private FragmentGameBinding binding;
  private int hearts = 3;
  private int score = 0;
  private int speed = 5; // placeholder
  private Note tonic;
  private Mode mode;
  private GameMode gameMode;

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
    tonic = GameFragmentArgs.fromBundle(getArguments()).getTonic();
    mode = GameFragmentArgs.fromBundle(getArguments()).getMode();
    gameMode = GameFragmentArgs.fromBundle(getArguments()).getGameMode();
    binding.pauseButton.setOnClickListener((v) -> {
      // TODO popup a dialog with volume toggle, resume, return to title screen buttons
      Intent intent = new Intent(getActivity(), MainActivity.class)
          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, hearts));
    binding.score.setText(getString(R.string.score_format, score));
    binding.scaleTitle.setText(
        getString(R.string.scale_title_format, tonic.toString().toUpperCase(),
            mode.toString().toLowerCase()));
    return binding.getRoot();
  }

  private void playLevel(Scale scale) {
// TODO pop up a dialog with the scale's notes
    Level level = new Level(scale);
    level.setHearts(hearts);
    level.setSpeed(speed);
    level.setScore(score);
    boolean wonLevel = level.play();
    // need a viewmodel here to get livedata of hearts/score
    hearts = level.getHearts();
    score = level.getScore();
    if (wonLevel) {
      // pop up a dialog saying congrats, you won!
    } else {
      // pop up a dialog saying sorry, you lost
    }
  }

  public enum GameMode {
    LEARN, CHALLENGE
  }

}