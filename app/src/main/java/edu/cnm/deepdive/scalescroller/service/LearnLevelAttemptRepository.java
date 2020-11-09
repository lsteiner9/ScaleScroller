package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.scalescroller.model.dao.LearnLevelAttemptDao;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import io.reactivex.Completable;
import java.util.List;

public class LearnLevelAttemptRepository {

  private final Context context;
  private final LearnLevelAttemptDao learnLevelAttemptDao;

  public LearnLevelAttemptRepository(Context context) {
    this.context = context;
    ScaleScrollerDatabase database = ScaleScrollerDatabase.getInstance();
    learnLevelAttemptDao = database.getLearnLevelAttemptDao();
  }

  public Completable save(LearnLevelAttempt attempt) {
    return (attempt.getId() == 0)
        ? learnLevelAttemptDao.insert(attempt)
            .doAfterSuccess(attempt::setId)
            .ignoreElement()
        : learnLevelAttemptDao.update(attempt)
            .ignoreElement();
  }

  public Completable delete(LearnLevelAttempt attempt) {
    return (attempt.getId() == 0)
        ? Completable.complete()
        : learnLevelAttemptDao.delete(attempt)
            .ignoreElement();
  }

  public LiveData<LearnLevelAttempt> get(long id) {
    return learnLevelAttemptDao.select(id);
  }

  public LiveData<List<LearnLevelAttempt>> getAll() {
    return learnLevelAttemptDao.selectAll();
  }

  public LiveData<List<LearnLevelAttempt>> getAllByPlayer(long playerId) {
    return learnLevelAttemptDao.selectAllWithPlayer(playerId);
  }

  public LiveData<LearnLevelAttempt> getHighestDifficulty(long playerId) {
    return learnLevelAttemptDao.selectHighestDifficultyWithPlayer(playerId);
  }

}
