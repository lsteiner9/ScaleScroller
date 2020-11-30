package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.ChallengeAttemptDao;
import edu.cnm.deepdive.scalescroller.model.dao.LearnLevelAttemptDao;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import io.reactivex.Completable;
import java.util.List;

/**
 * {@code LearnLevelAttemptRepository} contains methods that provide a layer of abstraction above
 * the {@link LearnLevelAttemptDao}, and allows for creation, reading, updating, and deleting of
 * attempts.
 */
public class LearnLevelAttemptRepository {

  private final Context context;
  private final LearnLevelAttemptDao learnLevelAttemptDao;

  /**
   * The constructor initializes the context, the database, and the dao.
   *
   * @param context The application context.
   */
  public LearnLevelAttemptRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    learnLevelAttemptDao = database.getLearnLevelAttemptDao();
  }

  /**
   * Creates or updates a attempt record in the database.
   *
   * @param attempt The {@code LearnLevelAttempt}.
   * @return A {@code Completable} indicating the success or failure of the creation/update.
   */
  public Completable save(LearnLevelAttempt attempt) {
    return (attempt.getId() == 0)
        ? learnLevelAttemptDao.insert(attempt)
        .doAfterSuccess(attempt::setId)
        .ignoreElement()
        : learnLevelAttemptDao.update(attempt)
            .ignoreElement();
  }

  /**
   * Deletes a attempt record in the database.
   *
   * @param attempt The {@code LearnLevelAttempt}.
   * @return A {@code Completable} indicating the success or failure of the deletion.
   */
  public Completable delete(LearnLevelAttempt attempt) {
    return (attempt.getId() == 0)
        ? Completable.complete()
        : learnLevelAttemptDao.delete(attempt)
            .ignoreElement();
  }

  /**
   * Returns LiveData of a learn level attempt, given its id.
   *
   * @param id The id of the attempt.
   * @return {@code LiveData} of a {@code LearnLevelAttempt}.
   */
  public LiveData<LearnLevelAttempt> get(long id) {
    return learnLevelAttemptDao.select(id);
  }

  /**
   * Returns LiveData of all learn level attempts.
   *
   * @return {@code LiveData} of a {@code List} of {@code LearnLevelAttempt}.
   */
  public LiveData<List<LearnLevelAttempt>> getAll() {
    return learnLevelAttemptDao.selectAll();
  }

  /**
   * Returns LiveData of all learn level attempts made by a specific player.
   *
   * @param playerId The player's id.
   * @return {@code LiveData} of a {@code List} of {@code LearnLevelAttempt}.
   */
  public LiveData<List<LearnLevelAttempt>> getAllByPlayer(long playerId) {
    return learnLevelAttemptDao.selectAllWithPlayer(playerId);
  }

  /**
   * Returns LiveData of the learn level attempt with the highest difficulty rating.
   *
   * @param playerId The player's id.
   * @return {@code LiveData} of a {@code LearnLevelAttempt}.
   */
  public LiveData<LearnLevelAttempt> getHighestDifficulty(long playerId) {
    return learnLevelAttemptDao.selectHighestDifficultyWithPlayer(playerId);
  }

}
