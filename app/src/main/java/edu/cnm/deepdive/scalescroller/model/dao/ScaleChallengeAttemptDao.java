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

/**
 * Provides an interface with methods that perform actions on the {@link ScaleChallengeAttempt}
 * table of the database.
 */
@Dao
public interface ScaleChallengeAttemptDao {

  /**
   * A query which selects challenge attempts for a single scale id, ordering by score from highest
   * to lowest.
   */
  String SELECT_CHALLENGE_ATTEMPTS_ORDER_BY_SCORE_QUERY = "SELECT ca.* FROM ChallengeAttempt AS ca "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.challenge_attempt_id = ca.challenge_attempt_id "
      + "WHERE sca.scale_id = :scaleId ORDER BY ca.total_score DESC";

  /**
   * A query which selects challenge attempts for a single scale id, ordering by timestamp from most
   * recent to less recent.
   */
  String SELECT_CHALLENGE_ATTEMPTS_ORDER_BY_DATE_QUERY = "SELECT ca.* FROM ChallengeAttempt AS ca "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.challenge_attempt_id = ca.challenge_attempt_id "
      + "WHERE sca.scale_id = :scaleId ORDER BY ca.timestamp DESC";

  /**
   * A query which selects scales for a single challenge attempt id, ordering by scale name.
   */
  String SELECT_SCALES_BY_NAME_QUERY = "SELECT s.* FROM Scale AS s "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.scale_id = s.scale_id "
      + "WHERE sca.challenge_attempt_id = :attemptId ORDER BY s.mode AND s.tonic ASC";

  /**
   * A query which selects scales for a single challenge attempt id, ordering by timestamp.
   */
  String SELECT_SCALES_BY_DATE_QUERY = "SELECT s.* FROM Scale AS s "
      + "INNER JOIN ScaleChallengeAttempt AS sca ON sca.scale_id = s.scale_id "
      + "WHERE sca.challenge_attempt_id = :attemptId ORDER BY sca.timestamp ASC";

  /**
   * Inserts a single {@code ScaleChallengeAttempt} into the database.
   *
   * @param attempt The attempt to be inserted.
   * @return A {@code Single} holding the id of the attempt that was inserted.
   */
  @Insert
  Single<Long> insert(ScaleChallengeAttempt attempt);

  /**
   * Inserts multiple attempts into the database.
   *
   * @param attempts The attempts to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of attempts that were inserted.
   */
  @Insert
  Single<List<Long>> insert(ScaleChallengeAttempt... attempts);

  /**
   * Inserts multiple attempts into the database.
   *
   * @param attempts The attempts to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of attempts that were inserted.
   */
  @Insert
  Single<List<Long>> insert(Collection<ScaleChallengeAttempt> attempts);

  /**
   * Updates a single attempt in the database.
   *
   * @param attempt The attempt to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(ScaleChallengeAttempt attempt);

  /**
   * Updates multiple attempts in the database.
   *
   * @param attempts The attempts to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(ScaleChallengeAttempt... attempts);

  /**
   * Updates multiple attempts in the database.
   *
   * @param attempts The attempts to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Collection<ScaleChallengeAttempt> attempts);

  /**
   * Deletes a single attempt from the database.
   *
   * @param attempt The attempt to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(ScaleChallengeAttempt attempt);

  /**
   * Deletes multiple attempts from the database.
   *
   * @param attempts The attempts to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(ScaleChallengeAttempt... attempts);

  /**
   * Deletes multiple attempts from the database.
   *
   * @param attempts The attempts to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Collection<ScaleChallengeAttempt> attempts);

  /**
   * Queries the database for a single attempt, based on id.
   *
   * @param id The id of the attempt.
   * @return {@code LiveData} of the selected attempt.
   */
  @Query("SELECT * FROM ScaleChallengeAttempt WHERE scale_challenge_attempt_id = :id")
  LiveData<ScaleChallengeAttempt> select(long id);

  /**
   * Queries the database for all attempts.
   *
   * @return {@code LiveData} of a {@code List} of attempts.
   */
  @Query("SELECT * FROM ScaleChallengeAttempt")
  LiveData<List<ScaleChallengeAttempt>> selectAll();

  /**
   * Queries the database for challenge attempts based on a scale id, ordering by score from highest
   * to lowest.
   *
   * @param scaleId The id of the scale.
   * @return {@code LiveData} of a {@code List} of {@link ChallengeAttempt}.
   */
  @Query(SELECT_CHALLENGE_ATTEMPTS_ORDER_BY_SCORE_QUERY)
  LiveData<List<ChallengeAttempt>> selectChallengeAttemptsOrderByScore(long scaleId);

  /**
   * Queries the database for challenge attempts based on a scale id, ordering by timestamp from
   * most recent to less recent.
   *
   * @param scaleId The id of the scale.
   * @return {@code LiveData} of a {@code List} of {@link ChallengeAttempt}.
   */
  @Query(SELECT_CHALLENGE_ATTEMPTS_ORDER_BY_DATE_QUERY)
  LiveData<List<ChallengeAttempt>> selectChallengeAttemptsOrderByDate(long scaleId);

  /**
   * Queries the database for scales based on a challenge attempt id, ordering by scale name.
   *
   * @param attemptId The id of the ChallengeAttempt.
   * @return {@code LiveData} of a {@code List} of {@link Scale}.
   */
  @Query(SELECT_SCALES_BY_NAME_QUERY)
  LiveData<List<Scale>> selectScalesOrderByName(long attemptId);

  /**
   * Queries the database for scales based on a challenge attempt id, ordering by timestamp.
   *
   * @param attemptId The id of the ChallengeAttempt
   * @return {@code LiveData} of a {@code List} of {@link Scale}.
   */
  @Query(SELECT_SCALES_BY_DATE_QUERY)
  LiveData<List<Scale>> selectScalesOrderByDate(long attemptId);

}
