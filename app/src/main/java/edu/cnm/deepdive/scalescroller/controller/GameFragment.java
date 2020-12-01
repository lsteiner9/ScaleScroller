package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.FragmentGameBinding;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.LearnLevelAttemptRepository;
import edu.cnm.deepdive.scalescroller.viewmodel.MainViewModel;

// TODO javadoc
public class GameFragment extends Fragment {

  private LearnLevelAttemptRepository learnLevelAttemptRepository;
  private ChallengeAttemptRepository challengeAttemptRepository;
  private FragmentGameBinding binding;
  private NavController navController;
  private MainViewModel viewModel;
  private Note tonic;
  private Mode mode;
  private GameMode gameMode;
  private int score = 0;
  private int hearts = 3;
  private int speed;

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
//    speed = args.getInt("fix this to make it get speed from shared preferences");
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentGameBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    binding.pauseButton.setOnClickListener((v) -> {
      // TODO popup a dialog with volume toggle, resume, return to title screen buttons
      navController.navigate(GameFragmentDirections.openTitle());
    });
    //noinspection ConstantConditions
    gameMode = GameFragmentArgs.fromBundle(getArguments()).getGameMode();
    if (gameMode == GameMode.LEARN) {
      tonic = GameFragmentArgs.fromBundle(getArguments()).getTonic();
      mode = GameFragmentArgs.fromBundle(getArguments()).getMode();
    }
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, hearts));
    binding.score.setText(getString(R.string.score_format, score));
    binding.scaleTitle.setText(
        getString(R.string.scale_title_format, tonic.toString().toUpperCase(),
            mode.toString().toLowerCase()));
    return binding.getRoot();
  }

  private void playLevel(Scale scale) {
    navController.navigate(GameFragmentDirections.openScaleDialog());
    //do some stuff in here
    Level level = new Level(scale);
    boolean wonLevel = level.play();
    if (wonLevel) {
      // pop up a dialog saying congrats, you won!
    } else {
      // pop up a dialog saying sorry, you lost
    }
  }

  private void setupViewModel() {
    FragmentActivity activity = getActivity();
    //noinspection ConstantConditions
    viewModel = new ViewModelProvider(activity).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    viewModel.getLevel().observe(lifecycleOwner, this::updateGameDisplay);
  }

  private void updateGameDisplay(Level level) {
    hearts = level.getHearts();
    score = level.getScore();
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, hearts));
    binding.score.setText(getString(R.string.score_format, score));
  }

  public enum GameMode {
    LEARN, CHALLENGE
  }

}