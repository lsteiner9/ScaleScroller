package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ScaleChallengeAttemptDao {

  String SELECT_CHALLENGE_ATTEMPTS_BY_SCORE_QUERY = "SELECT ca.* FROM ChallengeAttempt AS ca "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.challenge_attempt_id = ca.challenge_attempt_id "
      + "WHERE sca.scale_id = :scaleId ORDER BY ca.total_score DESC";

  String SELECT_CHALLENGE_ATTEMPTS_BY_DATE_QUERY = "SELECT ca.* FROM ChallengeAttempt AS ca "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.challenge_attempt_id = ca.challenge_attempt_id "
      + "WHERE sca.scale_id = :scaleId ORDER BY ca.timestamp DESC";

  String SELECT_SCALES_BY_NAME_QUERY = "SELECT s.* FROM Scale AS s "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.scale_id = s.scale_id "
      + "WHERE sca.challenge_attempt_id = :attemptId ORDER BY s.mode AND s.tonic ASC";

  String SELECT_SCALES_BY_DATE_QUERY = "SELECT s.* FROM Scale AS s "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.scale_id = s.scale_id "
      + "WHERE sca.challenge_attempt_id = :attemptId ORDER BY sca.timestamp ASC";

  @Insert
  Single<Long> insert(ScaleChallengeAttempt attempt);

  @Insert
  Single<List<Long>> insert(ScaleChallengeAttempt... attempts);

  @Insert
  Single<List<Long>> insert(Collection<ScaleChallengeAttempt> attempts);

  @Update
  Single<Integer> update(ScaleChallengeAttempt attempt);

  @Update
  Single<Integer> update(ScaleChallengeAttempt... attempts);

  @Update
  Single<Integer> update(Collection<ScaleChallengeAttempt> attempts);

  @Delete
  Single<Integer> delete(ScaleChallengeAttempt attempt);

  @Delete
  Single<Integer> delete(ScaleChallengeAttempt... attempts);

  @Delete
  Single<Integer> delete(Collection<ScaleChallengeAttempt> attempts);

  @Query("SELECT * FROM ScaleChallengeAttempt WHERE scale_challenge_attempt_id = :id")
  LiveData<ScaleChallengeAttempt> select(long id);

  @Query("SELECT * FROM ScaleChallengeAttempt")
  LiveData<List<ScaleChallengeAttempt>> selectAll();

  @Query(SELECT_CHALLENGE_ATTEMPTS_BY_SCORE_QUERY)
  LiveData<List<ChallengeAttempt>> selectChallengeAttemptsOrderByScore(long scaleId);

  @Query(SELECT_CHALLENGE_ATTEMPTS_BY_DATE_QUERY)
  LiveData<List<ChallengeAttempt>> selectChallengeAttemptsOrderByDate(long scaleId);

  @Query(SELECT_SCALES_BY_NAME_QUERY)
  LiveData<List<Scale>> selectScalesOrderByName(long attemptId);

  @Query(SELECT_SCALES_BY_DATE_QUERY)
  LiveData<List<Scale>> selectScalesOrderByDate(long attemptId);

}
