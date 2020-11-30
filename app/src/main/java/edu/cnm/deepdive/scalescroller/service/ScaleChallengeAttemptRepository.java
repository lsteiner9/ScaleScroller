package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.LearnLevelAttemptDao;
import edu.cnm.deepdive.scalescroller.model.dao.ScaleChallengeAttemptDao;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import io.reactivex.Completable;
import java.util.List;

/**
 * {@code ScaleChallengeAttemptRepository} contains methods that provide a layer of abstraction
 * above the {@link ScaleChallengeAttemptDao}, and allows for creation, reading, updating, and
 * deleting of attempts.
 */
public class ScaleChallengeAttemptRepository {

  private final Context context;
  private final ScaleChallengeAttemptDao scaleChallengeAttemptDao;

  /**
   * The constructor initializes the context, the database, and the dao.
   *
   * @param context The application context.
   */
  public ScaleChallengeAttemptRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    scaleChallengeAttemptDao = database.getScaleChallengeAttemptDao();
  }

  /**
   * Creates or updates a attempt record in the database.
   *
   * @param attempt The {@code ScaleChallengeAttempt}.
   * @return A {@code Completable} indicating the success or failure of the creation/update.
   */
  public Completable save(ScaleChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? scaleChallengeAttemptDao.insert(attempt)
        .doAfterSuccess(attempt::setId)
        .ignoreElement()
        : scaleChallengeAttemptDao.update(attempt)
            .ignoreElement();
  }

  /**
   * Deletes a attempt record in the database.
   *
   * @param attempt The {@code ScaleChallengeAttempt}.
   * @return A {@code Completable} indicating the success or failure of the deletion.
   */
  public Completable delete(ScaleChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? Completable.complete()
        : scaleChallengeAttemptDao.delete(attempt)
            .ignoreElement();
  }

  /**
   * Returns LiveData of a scale-challenge attempt, given its id.
   *
   * @param id The id of the attempt.
   * @return {@code LiveData} of a {@code ScaleChallengeAttempt}.
   */
  public LiveData<ScaleChallengeAttempt> getScaleChallengeAttempt(long id) {
    return scaleChallengeAttemptDao.select(id);
  }

  /**
   * Returns LiveData of all scale-challenge attempts.
   *
   * @return {@code LiveData} of a {@code List} of {@code ScaleChallengeAttempt}.
   */
  public LiveData<List<ScaleChallengeAttempt>> getAllScaleChallengeAttempts() {
    return scaleChallengeAttemptDao.selectAll();
  }

  /**
   * Returns LiveData of all challenge attempts that use a particular scale, ordered by score.
   *
   * @param scaleId The scale's id.
   * @return {@code LiveData} of a {@code List} of {@link ChallengeAttempt}.
   */
  public LiveData<List<ChallengeAttempt>> getChallengeAttemptsForScaleOrderByScore(long scaleId) {
    return scaleChallengeAttemptDao.selectChallengeAttemptsOrderByScore(scaleId);
  }

  /**
   * Returns LiveData of all challenge attempts that use a particular scale, ordered by date.
   *
   * @param scaleId The scale's id.
   * @return {@code LiveData} of a {@code List} of {@link ChallengeAttempt}.
   */
  public LiveData<List<ChallengeAttempt>> getChallengeAttemptsForScaleOrderByDate(long scaleId) {
    return scaleChallengeAttemptDao.selectChallengeAttemptsOrderByDate(scaleId);
  }

  /**
   * Returns LiveData of all scales used in a particular attempt, ordered by scale name.
   *
   * @param attemptId The attempt's id.
   * @return {@code LiveData} of a {@code List} of {@link Scale}.
   */
  public LiveData<List<Scale>> getScalesForChallengeAttemptOrderByName(long attemptId) {
    return scaleChallengeAttemptDao.selectScalesOrderByName(attemptId);
  }

  /**
   * Returns LiveData of all scales used in a particular attempt, ordered by date.
   *
   * @param attemptId The attempt's id.
   * @return {@code LiveData} of a {@code List} of {@link Scale}.
   */
  public LiveData<List<Scale>> getScalesForChallengeAttemptOrderByDate(long attemptId) {
    return scaleChallengeAttemptDao.selectScalesOrderByDate(attemptId);
  }

}
