package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.ChallengeAttemptDao;
import edu.cnm.deepdive.scalescroller.model.dao.PlayerDao;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.pojo.ChallengeAttemptWithPlayer;
import io.reactivex.Completable;
import java.util.List;

/**
 * {@code ChallengeAttemptRepository} contains methods that provide a layer of abstraction above the
 * {@link ChallengeAttemptDao}, and allows for creation, reading, updating, and deleting of
 * attempts.
 */
public class ChallengeAttemptRepository {

  private final Context context;
  private final ChallengeAttemptDao challengeAttemptDao;

  /**
   * The constructor initializes the context, the database, and the dao.
   *
   * @param context The application context.
   */
  public ChallengeAttemptRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    challengeAttemptDao = database.getChallengeAttemptDao();
  }

  /**
   * Creates or updates a attempt record in the database.
   *
   * @param attempt The {@code ChallengeAttempt}.
   * @return A {@code Completable} indicating the success or failure of the creation/update.
   */
  public Completable save(ChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? challengeAttemptDao.insert(attempt)
        .doAfterSuccess(attempt::setId)
        .ignoreElement()
        : challengeAttemptDao.update(attempt)
            .ignoreElement();
  }

  /**
   * Deletes a attempt record in the database.
   *
   * @param attempt The {@code ChallengeAttempt}.
   * @return A {@code Completable} indicating the success or failure of the deletion.
   */
  public Completable delete(ChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? Completable.complete()
        : challengeAttemptDao.delete(attempt)
            .ignoreElement();
  }

  /**
   * Returns LiveData of a challenge attempt, given its id.
   *
   * @param id The id of the attempt.
   * @return {@code LiveData} of a {@code ChallengeAttempt}.
   */
  public LiveData<ChallengeAttempt> get(long id) {
    return challengeAttemptDao.select(id);
  }

  /**
   * Returns LiveData of all challenge attempts.
   *
   * @return {@code LiveData} of a {@code List} of {@code ChallengeAttempt}.
   */
  public LiveData<List<ChallengeAttempt>> getAll() {
    return challengeAttemptDao.selectAll();
  }

  /**
   * Returns LiveData of all challenge attempts made by a specific player.
   *
   * @param id The player's id.
   * @return {@code LiveData} of a {@code List} of {@code ChallengeAttempt}.
   */
  public LiveData<List<ChallengeAttempt>> getByPlayer(long id) {
    return challengeAttemptDao.selectAllWithPlayer(id);
  }

  /**
   * Returns LiveData of the challenge attempts with the highest scores.
   *
   * @param numScores The number of scores to return.
   * @return {@code LiveData} of a {@code List} of {@code ChallengeAttemptWithPlayer}.
   */
  public LiveData<List<ChallengeAttemptWithPlayer>> getHighScores(int numScores) {
    return challengeAttemptDao.selectHighScores(numScores);
  }

  /**
   * Returns LiveData of the challenge attempts with the highest scores made by a particular
   * player.
   *
   * @param id        The player's id.
   * @param numScores The number of scores to return.
   * @return {@code LiveData} of a {@code List} of {@code ChallengeAttempt}.
   */
  public LiveData<List<ChallengeAttempt>> getHighScoresByPlayer(long id, int numScores) {
    return challengeAttemptDao.selectPlayerHighScores(id, numScores);
  }

}
