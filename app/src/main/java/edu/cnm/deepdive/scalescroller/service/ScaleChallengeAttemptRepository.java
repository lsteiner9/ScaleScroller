package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.ScaleChallengeAttemptDao;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import io.reactivex.Completable;
import java.util.List;

public class ScaleChallengeAttemptRepository {

  private final Context context;
  private final ScaleChallengeAttemptDao scaleChallengeAttemptDao;

  public ScaleChallengeAttemptRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    scaleChallengeAttemptDao = database.getScaleChallengeAttemptDao();
  }

  public Completable save(ScaleChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? scaleChallengeAttemptDao.insert(attempt)
        .doAfterSuccess(attempt::setId)
        .ignoreElement()
        : scaleChallengeAttemptDao.update(attempt)
            .ignoreElement();
  }

  public Completable delete(ScaleChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? Completable.complete()
        : scaleChallengeAttemptDao.delete(attempt)
            .ignoreElement();
  }

  public LiveData<ScaleChallengeAttempt> getScaleChallengeAttempt(long id) {
    return scaleChallengeAttemptDao.select(id);
  }

  public LiveData<List<ScaleChallengeAttempt>> getAllScaleChallengeAttempts() {
    return scaleChallengeAttemptDao.selectAll();
  }

  public LiveData<List<ChallengeAttempt>> getChallengeAttemptsForScaleOrderByScore(long scaleId) {
    return scaleChallengeAttemptDao.selectChallengeAttemptsOrderByScore(scaleId);
  }

  public LiveData<List<ChallengeAttempt>> getChallengeAttemptsForScaleOrderByDate(long scaleId) {
    return scaleChallengeAttemptDao.selectChallengeAttemptsOrderByDate(scaleId);
  }

  public LiveData<List<Scale>> getScalesForChallengeAttemptOrderByName(long attemptId) {
    return scaleChallengeAttemptDao.selectScalesOrderByName(attemptId);
  }

  public LiveData<List<Scale>> getScalesForChallengeAttemptOrderByDate(long attemptId) {
    return scaleChallengeAttemptDao.selectScalesOrderByDate(attemptId);
  }

}
