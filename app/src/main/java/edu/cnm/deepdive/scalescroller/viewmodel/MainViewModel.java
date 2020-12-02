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
import edu.cnm.deepdive.scalescroller.model.pojo.ChallengeAttemptWithPlayer;
import edu.cnm.deepdive.scalescroller.service.ChallengeAttemptRepository;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.service.PlayerRepository;
import edu.cnm.deepdive.scalescroller.service.ScaleRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * Serves as the ViewModel for all fragments except the GameFragment.
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private static final int DEFAULT_NUM_SCORES = 10;

  private final ChallengeAttemptRepository challengeAttemptRepository;
  private final PlayerRepository playerRepository;
  private final ScaleRepository scaleRepository;

  private final MutableLiveData<ChallengeAttempt> challengeAttempt;

  private final CompositeDisposable pending;
  private final SharedPreferences preferences;
  private final GoogleSignInService signInService;

  /**
   * The constructor initializes repositories, services, and other elements needed by the
   * ViewModel.
   *
   * @param application The ScaleScroller application.
   */
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

  /**
   * Returns LiveData of the current player.
   *
   * @return {@code LiveData} of the current {@link Player}.
   */
  public LiveData<Player> getPlayer() {
    return playerRepository.getByOauth(signInService.getAccount().getId());
  }

  /**
   * Returns LiveData of a list of the highest-scoring challenge attempts. Uses the default number
   * of high scores.
   *
   * @return {@code LiveData} of a {@code List} of the highest-scoring {@link ChallengeAttempt}.
   */
  public LiveData<List<ChallengeAttemptWithPlayer>> getHighScores() {
    return challengeAttemptRepository.getHighScores(DEFAULT_NUM_SCORES);
  }

  /**
   * Returns LiveData of a list of all scales in the database, ordered by difficulty.
   *
   * @return {@code LiveData} of a {@code List} of all {@link Scale} in the database.
   */
  public LiveData<List<Scale>> getScales() {
    return scaleRepository.getAllOrdered();
  }

}
