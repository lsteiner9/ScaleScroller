package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.ChallengeAttemptDao;
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

  public Completable save(ScaleChallengeAttempt scaleChallengeAttempt) {
    return (scaleChallengeAttempt.getId() == 0)
        ? scaleChallengeAttemptDao.insert(scaleChallengeAttempt)
        .doAfterSuccess(scaleChallengeAttempt::setId)
        .ignoreElement()
        : scaleChallengeAttemptDao.update(scaleChallengeAttempt)
            .ignoreElement();
  }

  public Completable delete(ScaleChallengeAttempt scaleChallengeAttempt) {
    return (scaleChallengeAttempt.getId() == 0)
        ? Completable.complete()
        : scaleChallengeAttemptDao.delete(scaleChallengeAttempt)
            .ignoreElement();
  }

  public LiveData<ScaleChallengeAttempt> getScaleChallengeAttempt(long id) {
    return scaleChallengeAttemptDao.select(id);
  }

  public LiveData<List<ScaleChallengeAttempt>> getAllScaleChallengeAttempts() {
    return scaleChallengeAttemptDao.selectAll();
  }

  public LiveData<List<ChallengeAttempt>> getChallengeAttemptsForScaleByScore(long scaleId) {
    return scaleChallengeAttemptDao.selectChallengeAttemptsByScore(scaleId);
  }

  public LiveData<List<ChallengeAttempt>> getChallengeAttemptsForScaleByDate(long scaleId) {
    return scaleChallengeAttemptDao.selectChallengeAttemptsByDate(scaleId);
  }

  public LiveData<List<Scale>> getScalesForChallengeAttemptByName(long attemptId) {
    return scaleChallengeAttemptDao.selectScalesByName(attemptId);
  }

  public LiveData<List<Scale>> getScalesForChallengeAttemptByDate(long attemptId) {
    return scaleChallengeAttemptDao.selectScalesByDate(attemptId);
  }

}
