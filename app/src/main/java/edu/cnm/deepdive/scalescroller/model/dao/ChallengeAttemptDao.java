package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ChallengeAttemptDao {

  @Insert
  Single<Long> insert(ChallengeAttempt attempt);

  @Insert
  Single<List<Long>> insert(ChallengeAttempt... attempts);

  @Insert
  Single<List<Long>> insert(Collection<ChallengeAttempt> attempts);

  @Delete
  Single<Integer> delete(ChallengeAttempt attempt);

  @Delete
  Single<Integer> delete(ChallengeAttempt...attempts);

  @Delete
  Single<Integer> delete(Collection<ChallengeAttempt> attempts);

  @Query("SELECT * FROM ChallengeAttempt WHERE player_id = :id ORDER BY timestamp DESC")
  LiveData<ChallengeAttempt> select(long id);

  @Query("SELECT * FROM ChallengeAttempt WHERE player_id = :id ORDER BY total_score DESC LIMIT 10")
  LiveData<ChallengeAttempt> selectPlayerHighScores(long id);

  @Query("SELECT * FROM ChallengeAttempt ORDER BY total_score DESC LIMIT 10")
  LiveData<ChallengeAttempt> selectHighScores();
}
