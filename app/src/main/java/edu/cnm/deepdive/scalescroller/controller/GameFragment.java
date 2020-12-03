package edu.cnm.deepdive.scalescroller.controller;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.FragmentGameBinding;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The GameFragment is where the gameplay takes place, for both Learn and Challenge modes.
 */
public class GameFragment extends Fragment {

  private static final int INITIAL_HEARTS = 3;
  private static final int INITIAL_SCORE = 0;

  private SharedPreferences preferences;
  private MediaPlayer mediaPlayer;
  private FragmentGameBinding binding;
  private NavController navController;
  private GameViewModel viewModel;
  private GameFragmentArgs args;
  private GoogleSignInService signInService;

  private String volumePrefKey;
  private int volumePrefDefault;
  private int volume;
  private String audioTogglePrefKey;
  private boolean audioToggle;
  private String speedPrefKey;
  private int speedPrefDefault;
  private int speed;

  private Level level;
  private Scale scale;
  private Note tonic;
  private Mode mode;
  private GameMode gameMode;
  private List<Scale> scales;
  private String notes;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //noinspection ConstantConditions
    args = GameFragmentArgs.fromBundle(getArguments());
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentGameBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    signInService = GoogleSignInService.getInstance();
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
    audioTogglePrefKey = getString(R.string.audio_toggle_key);
    audioToggle = preferences.getBoolean(audioTogglePrefKey, true);

    //speed will be used once the SurfaceView is implemented - no way to really use it at this point
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
    viewModel.getLevelWon().observe(lifecycleOwner, this::finishGame);
    viewModel.getResume().observe(lifecycleOwner, (resume) -> {
      if (!resume) {
        navController.navigate(GameFragmentDirections.openTitle());
        //TODO clear scale, score, hearts data from viewmodel - it keeps all scale info from previous level
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
    binding.score.setText(getString(R.string.score_format, INITIAL_SCORE));
    binding.hearts.setText(getString(R.string.placeholder_for_hearts, INITIAL_HEARTS));
    viewModel.setPaused(true);
    navController.navigate(GameFragmentDirections.openScaleDialog(
        tonic.toString().toUpperCase(), mode.toString().toLowerCase(), viewModel.getNotesString()));
  }

  private void startGame() {
    playSound(R.raw.start_level);
    Random rng = new SecureRandom();
//    Note[] correctNotes = viewModel.getNotes();

//    Instead, using a placeholder correctNotes array for now
    Note[] correctNotes = new Note[]{Note.F_SHARP, Note.C};
    Note[] possibleNotes = Arrays.stream(Note.values()).filter((note) -> {
      for (Note correct : correctNotes) {
        if (note == correct) {
          return false;
        }
      }
      return true;
    }).toArray(Note[]::new);
    int length = correctNotes.length;
    int bound = possibleNotes.length;
//    Note[] incorrectNotes = new Note[length];
//    for (int i = 0; i < incorrectNotes.length; i++) {
//      incorrectNotes[i] = possibleNotes[rng.nextInt(bound)];
//    }

//    Instead, a placeholder incorrectNotes array for now
    Note[] incorrectNotes = new Note[]{Note.B_FLAT};

    binding.coin1.setText(correctNotes[rng.nextInt(length)].toString());
    binding.coin2.setText(correctNotes[rng.nextInt(length)].toString());
    binding.coin3.setText(incorrectNotes[rng.nextInt(incorrectNotes.length)].toString());

    binding.coin1.setOnClickListener((v) -> {
      playSound(R.raw.correct_coin);
      binding.coin1.setTextColor(R.color.correctCoin);
      //TODO This only sets the text color, not the background color; it also doesn't even set the right color
      viewModel.getLevel().setScore(R.integer.coin_score);
    });
    binding.coin2.setOnClickListener((v) -> {
      playSound(R.raw.correct_coin);
      binding.coin2.setTextColor(R.color.correctCoin);
      viewModel.getLevel().setScore(R.integer.coin_score);
    });
    binding.coin3.setOnClickListener((v) -> {
      playSound(R.raw.incorrect_coin);
      binding.coin3.setTextColor(R.color.incorrectCoin);
      viewModel.getLevel().setHearts(false);
    });
  }

  private void finishGame(Boolean levelWon) {
    if (levelWon != null) {
      LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
      if (gameMode == GameMode.CHALLENGE) {
        ChallengeAttempt challengeAttempt = new ChallengeAttempt();
        viewModel.getPlayer()
            .observe(lifecycleOwner,
                player -> challengeAttempt.setPlayerId(player.getId()));
        //Placeholders
        challengeAttempt.setCorrectCoins(0);
        challengeAttempt.setIncorrectCoins(0);
        viewModel.getScale(tonic, mode).observe(lifecycleOwner,
            scale -> challengeAttempt.setLastScaleId(scale.getId()));
        challengeAttempt.setTimestamp(new Date());
        viewModel.getScore().observe(lifecycleOwner, challengeAttempt::setTotalScore);
        viewModel.save(challengeAttempt);
      } else {
        LearnLevelAttempt learnLevelAttempt = new LearnLevelAttempt();
        viewModel.getPlayer()
            .observe(lifecycleOwner,
                player -> learnLevelAttempt.setPlayerId(player.getId()));
        //Placeholders
        learnLevelAttempt.setCorrectCoins(0);
        learnLevelAttempt.setIncorrectCoins(0);
        viewModel.getScale(tonic, mode).observe(lifecycleOwner, (scale) -> learnLevelAttempt.setDifficulty(scale.getDifficulty()));
        viewModel.getScale(tonic, mode).observe(lifecycleOwner, (scale) -> learnLevelAttempt.setScaleId(scale.getId()));
        learnLevelAttempt.setTimestamp(new Date());
        viewModel.save(learnLevelAttempt);
      }
      navController.navigate(GameFragmentDirections.openEndLevelDialog(gameMode.toString(),
          levelWon, viewModel.getLevel().getScore()));
    }
  }

  private void playSound(Integer resource) {
    mediaPlayer = MediaPlayer.create(getContext(), resource);
    if (!audioToggle) {
      mediaPlayer.setVolume(0.0f, 0.0f);
    } else {
      //TODO can't get volume adjustments to work
//      this should work, but doesn't. It's like the audio files I have can't be adjusted or something.
//      float vol = (float) (1 - (Math.log(R.integer.audio_max - volume) / Math.log(R.integer.audio_max)));
      mediaPlayer.setVolume(volume, volume);
    }
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