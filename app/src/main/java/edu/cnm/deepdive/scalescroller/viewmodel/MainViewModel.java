package edu.cnm.deepdive.scalescroller.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.service.PlayerRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

//TODO javadoc
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final int DEFAULT_NUM_SCORES = 10;

  private final ChallengeAttemptRepository challengeAttemptRepository;
  private final PlayerRepository playerRepository;
  private final ScaleRepository scaleRepository;

  private final MutableLiveData<ChallengeAttempt> challengeAttempt;

  private final CompositeDisposable pending;
  private final SharedPreferences preferences;
  private final GoogleSignInService signInService;

  public MainViewModel(@NonNull Application application) {
    super(application);
    challengeAttemptRepository = new ChallengeAttemptRepository(application);
    playerRepository = new PlayerRepository(application);
    scaleRepository = new ScaleRepository(application);
    challengeAttempt = new MutableLiveData<>();
    pending = new CompositeDisposable();
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    signInService = GoogleSignInService.getInstance();
  }

  public LiveData<Player> getPlayer() {
    return playerRepository.getByOauth(signInService.getAccount().getId());
  }

  //TODO get the player id for use here - how??
  public LiveData<List<ChallengeAttempt>> getHighScores() {
    return challengeAttemptRepository.getHighScores(DEFAULT_NUM_SCORES);
  }

  public LiveData<List<Scale>> getScales() {
    return scaleRepository.getAllOrdered();
  }

}
