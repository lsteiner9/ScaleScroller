package edu.cnm.deepdive.scalescroller.controller;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.adapter.ScaleRecyclerAdapter;
import edu.cnm.deepdive.scalescroller.databinding.FragmentGameBinding;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * The GameFragment is where the gameplay takes place, for both Learn and Challenge modes.
 */
public class GameFragment extends Fragment {

  private SharedPreferences preferences;
  private MediaPlayer mediaPlayer;
  private FragmentGameBinding binding;
  private NavController navController;
  private GameViewModel viewModel;
  private GameFragmentArgs args;

  private String volumePrefKey;
  private int volumePrefDefault;
  private int volume;
  private String speedPrefKey;
  private int speedPrefDefault;
  private int speed;

  private Scale scale;
  private Note tonic;
  private Mode mode;
  private GameMode gameMode;
  private List<Scale> scales;
  private String notes;
  private int score = 0;
  private int hearts = 3;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //noinspection ConstantConditions
    args = GameFragmentArgs.fromBundle(getArguments());
  }

  /**
   * Sets up bindings, preferences, and general data.
   *
   * @param inflater           A {@code LayoutInflater}
   * @param container          A {@code ViewGroup}
   * @param savedInstanceState A {@code Bundle}
   * @return A {@code View} object.
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentGameBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    FragmentActivity activity = getActivity();
    viewModel = new ViewModelProvider(activity).get(GameViewModel.class);
    binding.pauseButton.setOnClickListener((v) -> {
      viewModel.setPaused(true);
      //noinspection ConstantConditions
      Navigation.findNavController(getView()).navigate(GameFragmentDirections.openPauseDialog());
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
    setUpViews();
    startGame();
  }

  private void setupViewModel() {
    //noinspection ConstantConditions
    getLifecycle().addObserver(viewModel);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    gameMode = args.getGameMode();
    tonic = args.getTonic();
    mode = args.getMode();

    preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    volumePrefKey = getString(R.string.volume_slider_key);
    volumePrefDefault = getResources().getInteger(R.integer.audio_pref_default);
    volume = preferences.getInt(volumePrefKey, volumePrefDefault);
    speedPrefKey = getString(R.string.speed_pref_key);
    speedPrefDefault = getResources().getInteger(R.integer.speed_pref_default);
    speed = (gameMode == GameMode.CHALLENGE) ? speedPrefDefault
        : preferences.getInt(speedPrefKey, speedPrefDefault);

    viewModel.setGameMode(gameMode);
    if (gameMode != GameMode.CHALLENGE) {
      viewModel.setTonic(tonic);
      viewModel.setMode(mode);
    } else {
      tonic = viewModel.getTonic();
      mode = viewModel.getMode();
    }
    viewModel.getScales().observe(lifecycleOwner, (scales) -> {
      if (scales != null) {
        GameFragment.this.scales = scales;
      }
    });
    viewModel.getHearts().observe(lifecycleOwner, this::updateHearts);
    viewModel.getScore().observe(lifecycleOwner, this::updateScore);
    viewModel.getLevel().observe(lifecycleOwner, this::updateGameDisplay);
    viewModel.getResume().observe(lifecycleOwner, (resume) -> {
      if (!resume) {
        navController.navigate(GameFragmentDirections.openTitle());
        //TODO clear scale, score, hearts data from viewmodel?
      }
    });
  }

  private void updateScore(Integer score) {
    binding.score.setText(getString(R.string.score_format, score));
    playSound(R.raw.correct_coin);
  }

  private void updateHearts(Integer hearts) {
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, hearts));
    playSound(R.raw.incorrect_coin);
  }

  private void setUpViews() {
    binding.scaleTitle.setText(
        getString(R.string.scale_title_format, tonic.toString().toUpperCase(),
            mode.toString().toLowerCase()));
    binding.score.setText(getString(R.string.score_format, 0));
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, 3));
    viewModel.setPaused(true);
    navController.navigate(GameFragmentDirections.openScaleDialog(
        tonic.toString().toUpperCase(), mode.toString().toLowerCase(), viewModel.getNotesString()));
  }

  private void startGame() {
    playSound(R.raw.start_level);
    //do some stuff in here, maybe?
    //for challenge, need to call setRandomScale again but pass in the current list of scales
  }


  private void updateGameDisplay(Level level) {
    hearts = level.getHearts();
    score = level.getScore();
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, hearts));
    binding.score.setText(getString(R.string.score_format, score));
    if (false/*game is finished*/) {
      navController.navigate(GameFragmentDirections.openEndLevelDialog(gameMode.toString(),
          level.getLevelWon(), score));
    }
  }

  private void setRandomScale(Random rng) {
    int randomNum = rng.nextInt(scales.size());
    Scale randomScale = scales.get(randomNum);
    tonic = randomScale.getTonic();
    mode = randomScale.getMode();
  }

  private void playSound(Integer resource) {
    mediaPlayer = MediaPlayer.create(getContext(), resource);
    mediaPlayer.setVolume(0.0f, volume);
    mediaPlayer.start();
    mediaPlayer.setOnCompletionListener((ignored) -> mediaPlayer.release());
  }

  /**
   * Enumerates the two game modes - LEARN and CHALLENGE.
   */
  public enum GameMode {
    LEARN, CHALLENGE
  }

}