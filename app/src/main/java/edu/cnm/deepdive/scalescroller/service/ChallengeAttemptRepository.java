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

  public Completable save(ChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? challengeAttemptDao.insert(attempt)
        .doAfterSuccess(attempt::setId)
        .ignoreElement()
        : challengeAttemptDao.update(attempt)
            .ignoreElement();
  }

  public Completable delete(ChallengeAttempt attempt) {
    return (attempt.getId() == 0)
        ? Completable.complete()
        : challengeAttemptDao.delete(attempt)
            .ignoreElement();
  }

  public LiveData<ChallengeAttempt> get(long id) {
    return challengeAttemptDao.select(id);
  }

  public LiveData<List<ChallengeAttempt>> getAll() {
    return challengeAttemptDao.selectAll();
  }

  public LiveData<List<ChallengeAttempt>> getByPlayer(long id) {
    return challengeAttemptDao.selectAllWithPlayer(id);
  }

  public LiveData<List<ChallengeAttempt>> getHighScores(int numScores) {
    return challengeAttemptDao.selectHighScores(numScores);
  }

  public LiveData<List<ChallengeAttempt>> getHighScoresByPlayer(long id, int numScores) {
    return challengeAttemptDao.selectPlayerHighScores(id, numScores);
  }

}
