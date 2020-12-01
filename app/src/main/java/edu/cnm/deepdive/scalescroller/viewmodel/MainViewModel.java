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
import edu.cnm.deepdive.scalescroller.controller.GameFragment.GameMode;
import edu.cnm.deepdive.scalescroller.model.Level;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.LearnLevelAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.PlayerRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

//TODO javadoc
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

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

  private int hearts = 3;
  private int score = 0;
  private int speed = 5; //placeholder, idk what speeds will look like
  private Scale selectedScale;
  private Note tonic;
  private Mode mode;
  private GameMode gameMode = LEARN;
  private List<Scale> scales;

  public MainViewModel(@NonNull Application application) {
    super(application);
    playerRepository = new PlayerRepository(application);
    learnLevelAttemptRepository = new LearnLevelAttemptRepository(application);
    challengeAttemptRepository = new ChallengeAttemptRepository(application);
    scaleRepository = new ScaleRepository(application);
    scaleChallengeAttemptRepository = new ScaleChallengeAttemptRepository(application);

    level = new MutableLiveData<>();
    challengeAttempt = new MutableLiveData<>();
    scaleChallengeAttempt = new MutableLiveData<>();
    learnLevelAttempt = new MutableLiveData<>();
    levelWon = new MutableLiveData<>();
    throwable = new MutableLiveData<>();

//    how to get this information in here??
//    gameMode = ?;
    selectedScale = new Scale();
//    selectedScale.setMode(mode);
//    selectedScale.setTonic(tonic);
//    scales = scaleRepository.getAll().getValue();
    rng = new SecureRandom();
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    pending = new CompositeDisposable();
    if (gameMode == LEARN) {
//      startLearnLevel();
    } else {
//      startChallengeAttempt();
    }
  }

  public LiveData<Level> getLevel() {
    return level;
  }

  public LiveData<ChallengeAttempt> getChallengeAttempt() {
    return challengeAttempt;
  }

  public LiveData<ScaleChallengeAttempt> getScaleChallengeAttempt() {
    return scaleChallengeAttempt;
  }

  public LiveData<LearnLevelAttempt> getLearnLevelAttempt() {
    return learnLevelAttempt;
  }

  public LiveData<Boolean> getLevelWon() {
    return levelWon;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public Scale getScale() {
    return level.getValue().getScale();
  }

  public GameMode getGameMode() {
    return gameMode;
  }

  public void startLearnLevel() {
    Level level = new Level(selectedScale);
  }

  public void startChallengeAttempt() {
//    idk how to do this
//    while (hearts > 0) {
//      startScaleChallengeAttempt();
//    }
  }

  public LiveData<List<Scale>> getScales() {
    return scaleRepository.getAllOrdered();
  }

  public Scale getRandomScale() {
    if (scales.size() <= 0) {
      scales = scaleRepository.getAll().getValue();
    }
    int randomNum = rng.nextInt(scales.size());
    Scale randomScale = scales.get(randomNum);
    scales.remove(randomNum);
    return randomScale;
  }
}
