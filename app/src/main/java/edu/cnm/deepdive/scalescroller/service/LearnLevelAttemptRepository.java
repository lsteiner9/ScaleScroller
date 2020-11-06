package edu.cnm.deepdive.scalescroller.service;

import android.content.Context;
import edu.cnm.deepdive.scalescroller.model.dao.LearnLevelAttemptDao;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import io.reactivex.Completable;

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
}
