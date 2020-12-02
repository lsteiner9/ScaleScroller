package edu.cnm.deepdive.scalescroller.viewmodel;

import static edu.cnm.deepdive.scalescroller.controller.GameFragment.GameMode.LEARN;

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
import edu.cnm.deepdive.scalescroller.model.entity.Player;
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

//TODO javadoc
public class GameViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final int INITIAL_HEARTS = 3;
  private static final int INITIAL_SCORE = 0;

  private final MutableLiveData<Level> level;
  private final MutableLiveData<ChallengeAttempt> challengeAttempt;
  private final MutableLiveData<ScaleChallengeAttempt> scaleChallengeAttempt;
  private final MutableLiveData<LearnLevelAttempt> learnLevelAttempt;
  private final MutableLiveData<Boolean> levelWon;
  private final MutableLiveData<Throwable> throwable;

  private final CompositeDisposable pending;
  private final SharedPreferences preferences;
  private final Random rng;

  private final PlayerRepository playerRepository;
  private final LearnLevelAttemptRepository learnLevelAttemptRepository;
  private final ChallengeAttemptRepository challengeAttemptRepository;
  private final ScaleRepository scaleRepository;
  private final ScaleChallengeAttemptRepository scaleChallengeAttemptRepository;
  private final GoogleSignInService signInService;


  private int hearts;
  private int score;
  private String speedPrefKey;
  private int speedPrefDefault;
  private int speed;
  private Scale selectedScale;
  private Note tonic;
  private Mode mode;
  private GameMode gameMode;
  private List<Scale> scales;

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
    rng = new SecureRandom();
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    speedPrefKey = application.getString(R.string.speed_pref_key);
    speedPrefDefault = application.getResources().getInteger(R.integer.speed_pref_default);
    speed = preferences.getInt(speedPrefKey, speedPrefDefault);
    pending = new CompositeDisposable();
    hearts = INITIAL_HEARTS;
    score = INITIAL_SCORE;
  }

  public LiveData<Level> getLevel() {
    return level;
  }

  public LiveData<List<Scale>> getScales() {
    return scaleRepository.getAllOrdered();
  }


  public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
  }

  public void setTonic(Note tonic) {
    this.tonic = tonic;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public void startLearnLevel() {
//    Level level = new Level(selectedScale);
  }

  public void startChallengeAttempt() {
//    idk how to do this
//    while (hearts > 0) {
//      startScaleChallengeAttempt();
//    }
  }

}
