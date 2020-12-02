package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.FragmentGameBinding;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.LearnLevelAttemptRepository;
import edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

// TODO javadoc
public class GameFragment extends Fragment {

  private FragmentGameBinding binding;
  private NavController navController;
  private GameViewModel viewModel;
  private GameFragmentArgs args;

  private Note tonic;
  private Mode mode;
  private GameMode gameMode;
  private List<Scale> scales;

  private int score = 0;
  private int hearts = 3;
  private int speed; // do i even need this in here?

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //noinspection ConstantConditions
    args = GameFragmentArgs.fromBundle(getArguments());
    // Do whatever is necessary with args.
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentGameBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    binding.pauseButton.setOnClickListener((v) -> {
      //noinspection ConstantConditions
      Navigation.findNavController(getView()).navigate(GameFragmentDirections.openPauseDialog());
    });
    gameMode = args.getGameMode();
    tonic = args.getTonic();
    mode = args.getMode();
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
    setUpViews();
  }

  private void setupViewModel() {
    FragmentActivity activity = getActivity();
    //noinspection ConstantConditions
    viewModel = new ViewModelProvider(activity).get(GameViewModel.class);
    getLifecycle().addObserver(viewModel);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    viewModel.setGameMode(gameMode);
//why is scales returning null??
    if (gameMode == GameMode.CHALLENGE) {
      viewModel.getScales().observe(lifecycleOwner,
          scaleList -> scales = GameFragment.this.setRandomScale(scaleList, new SecureRandom()));
    }
    viewModel.setTonic(tonic);
    viewModel.setMode(mode);
    viewModel.getLevel().observe(lifecycleOwner, this::updateGameDisplay);
  }

  private void setUpViews() {
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, hearts));
    binding.score.setText(getString(R.string.score_format, score));
    binding.scaleTitle.setText(
        getString(R.string.scale_title_format, tonic.toString().toUpperCase(),
            mode.toString().toLowerCase()));
  }

  private void playLevel(Scale scale) {
    navController.navigate(GameFragmentDirections.openScaleDialog());
    //do some stuff in here, maybe?
    //for challenge, need to call setRandomScale again but pass in the current list of scales
  }


  private void updateGameDisplay(Level level) {
    hearts = level.getHearts();
    score = level.getScore();
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, hearts));
    binding.score.setText(getString(R.string.score_format, score));
    if (false/*game is finished*/) {
      navController.navigate(GameFragmentDirections.openEndLevelDialog());
    }
  }

  public List<Scale> setRandomScale(List<Scale> scales, Random rng) {
    if (scales.size() <= 0) {
      viewModel.getScales().observe(getViewLifecycleOwner(), (scaleList) ->
          GameFragment.this.scales = setRandomScale(scaleList, new SecureRandom()));
    } else {
      int randomNum = rng.nextInt(scales.size());
      Scale randomScale = scales.get(randomNum);
      scales.remove(randomNum);
      tonic = randomScale.getTonic();
      mode = randomScale.getMode();
    }
    return scales;
  }

  public enum GameMode {
    LEARN, CHALLENGE
  }

}