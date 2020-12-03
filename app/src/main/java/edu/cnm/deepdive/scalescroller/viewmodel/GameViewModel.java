package edu.cnm.deepdive.scalescroller.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.controller.GameFragment.GameMode;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.service.LearnLevelAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.PlayerRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

//TODO do game logic

/**
 * Serves as the ViewModel for the GameFragment.
 */
public class GameViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final int INITIAL_HEARTS = 3;
  private static final int INITIAL_SCORE = 0;

  private final MutableLiveData<Level> level;
  private final MutableLiveData<ChallengeAttempt> challengeAttempt;
  private final MutableLiveData<ScaleChallengeAttempt> scaleChallengeAttempt;
  private final MutableLiveData<LearnLevelAttempt> learnLevelAttempt;
  private final MutableLiveData<Boolean> levelWon;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Integer> hearts;
  private final MutableLiveData<Integer> score;
  private final MutableLiveData<Boolean> resume;

  private final CompositeDisposable pending;
  private final SharedPreferences preferences;
  private final Random rng;
  private final PlayerRepository playerRepository;
  private final LearnLevelAttemptRepository learnLevelAttemptRepository;
  private final ChallengeAttemptRepository challengeAttemptRepository;
  private final ScaleRepository scaleRepository;
  private final ScaleChallengeAttemptRepository scaleChallengeAttemptRepository;
  private final GoogleSignInService signInService;

  private String speedPrefKey;
  private int speedPrefDefault;
  private int speed;
  private Note tonic;
  private Mode mode;
  private GameMode gameMode;

  /**
   * The constructor initializes repositories, services, and other elements needed by the
   * ViewModel.
   *
   * @param application The ScaleScroller application.
   */
  public GameViewModel(@NonNull Application application) {
    super(application);
    playerRepository = new PlayerRepository(application);
    learnLevelAttemptRepository = new LearnLevelAttemptRepository(application);
    challengeAttemptRepository = new ChallengeAttemptRepository(application);
    scaleRepository = new ScaleRepository(application);
    scaleChallengeAttemptRepository = new ScaleChallengeAttemptRepository(application);
    signInService = GoogleSignInService.getInstance();

    level = new MutableLiveData<>();
    challengeAttempt = new MutableLiveData<>();
    scaleChallengeAttempt = new MutableLiveData<>();
    learnLevelAttempt = new MutableLiveData<>();
    levelWon = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    hearts = new MutableLiveData<>();
    score = new MutableLiveData<>();
    resume = new MutableLiveData<>(true);
    rng = new SecureRandom();
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    speedPrefKey = application.getString(R.string.speed_pref_key);
    speedPrefDefault = application.getResources().getInteger(R.integer.speed_pref_default);
    speed = preferences.getInt(speedPrefKey, speedPrefDefault);
    pending = new CompositeDisposable();
  }

  /**
   * Returns LiveData of a level.
   *
   * @return {@code LiveData} of the current {@link Level}
   */
  public LiveData<Level> getLevel() {
    return level;
  }

  /**
   * Returns LiveData of a list of scales, ordered by difficulty.
   *
   * @return {@code LiveData} of a {@code List} of {@link Scale}
   */
  public LiveData<List<Scale>> getScales() {
    return scaleRepository.getAllOrdered();
  }

  /**
   * Returns LiveData of a Boolean value indicating dialog directions.
   *
   * @return {@code LiveData} of a {@code Boolean}.
   */
  public LiveData<Boolean> getResume() {
    return resume;
  }

  /**
   * Sets the resume value indicating dialog directions.
   *
   * @param resume A boolean value.
   */
  public void setResume(boolean resume) {
    this.resume.setValue(resume);
  }

  /**
   * Returns LiveData of an Integer value that holds the player's hearts.
   *
   * @return {@code LiveData} of a {@code Integer}.
   */
  public LiveData<Integer> getHearts() {
    return hearts;
  }

  /**
   * Returns LiveData of an Integer value that holds the player's score.
   *
   * @return {@code LiveData} of a {@code Integer}.
   */
  public LiveData<Integer> getScore() {
    return score;
  }

  /**
   * Sets the GameMode of the ViewModel to the enumerated types LEARN or CHALLENGE. If Challenge
   * mode has been selected, the default speed is used to start, ignoring any user preferences set
   * for Learn mode.
   *
   * @param gameMode A enumerated {@link GameMode} type.
   */
  public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
    if (gameMode == GameMode.CHALLENGE) {
      speed = speedPrefDefault;
    }
  }

  /**
   * Sets the tonic of the scale in the ViewModel to one of the enumerated types.
   *
   * @param tonic A enumerated {@link Note} type.
   */
  public void setTonic(Note tonic) {
    this.tonic = tonic;
  }

  /**
   * Sets the mode of the scale in the ViewModel to one of the enumerated types.
   *
   * @param mode A enumerated {@link Mode} type.
   */
  public void setMode(Mode mode) {
    this.mode = mode;
  }

  /**
   * Starts a single level. If the level is for Learn mode, uses the speed from SharedPreferences
   * (set in the constructor). If the level is for Challenge mode, increments the speed upon
   * completion of the level.
   */
  public void startLevel() {
    //start the level, do stuff
    if (gameMode == GameMode.CHALLENGE) {
      speed++;
    }
  }

}
