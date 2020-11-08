package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.ChallengeAttemptDao;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import io.reactivex.Completable;
import java.util.List;

public class ChallengeAttemptRepository {

  private final Context context;
  private final ChallengeAttemptDao challengeAttemptDao;

  public ChallengeAttemptRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    challengeAttemptDao = database.getChallengeAttemptDao();
  }

  public Completable save(ChallengeAttempt challengeAttempt) {
    return (challengeAttempt.getId() == 0)
        ? challengeAttemptDao.insert(challengeAttempt)
        .doAfterSuccess(challengeAttempt::setId)
        .ignoreElement()
        : challengeAttemptDao.update(challengeAttempt)
            .ignoreElement();
  }

  public Completable delete(ChallengeAttempt challengeAttempt) {
    return (challengeAttempt.getId() == 0)
        ? Completable.complete()
        : challengeAttemptDao.delete(challengeAttempt)
            .ignoreElement();
  }

  public LiveData<ChallengeAttempt> getChallengeAttempt(long id) {
    return challengeAttemptDao.select(id);
  }

  public LiveData<List<ChallengeAttempt>> getAllChallengeAttempts() {
    return challengeAttemptDao.selectAll();
  }

  public LiveData<List<ChallengeAttempt>> getChallengeAttemptsByPlayer(long id) {
    return challengeAttemptDao.selectAllFromPlayer(id);
  }

  public LiveData<List<ChallengeAttempt>> getHighScoresByPlayer(long id, int numScores) {
    return challengeAttemptDao.selectPlayerHighScores(id, numScores);
  }

  public LiveData<List<ChallengeAttempt>> getAllHighScores(int numScores) {
    return challengeAttemptDao.selectHighScores(numScores);
  }
}
