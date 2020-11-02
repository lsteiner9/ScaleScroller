package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.scalescroller.model.entity.LearnLevelAttempt;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface LearnLevelAttemptDao {

  @Insert
  Single<Long> insert(LearnLevelAttempt attempt);

  @Insert
  Single<List<Long>> insert(LearnLevelAttempt...attempts);

  @Insert
  Single<List<Long>> insert(Collection<LearnLevelAttempt> attempts);

  @Update
  Single<Integer> update(LearnLevelAttempt attempt);

  @Update
  Single<Integer> update(LearnLevelAttempt...attempts);

  @Update
  Single<Integer> update(Collection<LearnLevelAttempt> attempts);

  @Delete
  Single<Integer> delete(LearnLevelAttempt attempt);

  @Delete
  Single<Integer> delete(LearnLevelAttempt... attempts);

  @Delete
  Single<Integer> delete(Collection<LearnLevelAttempt> attempts);

  @Query("SELECT * FROM LearnLevelAttempt WHERE player_id = :id")
  LiveData<LearnLevelAttempt> select(long id);

  @Query("SELECT * FROM LearnLevelAttempt ORDER BY difficulty DESC LIMIT 1")
  LiveData<LearnLevelAttempt> selectHighestDifficulty();
}
