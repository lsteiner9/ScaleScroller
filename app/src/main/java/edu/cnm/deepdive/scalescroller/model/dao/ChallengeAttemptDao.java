package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.pojo.ChallengeAttemptWithPlayer;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * Provides an interface with methods that perform actions on the {@link ChallengeAttempt} table of
 * the database.
 */
@Dao
public interface ChallengeAttemptDao {

  /**
   * Inserts a single attempt into the database.
   *
   * @param attempt The attempt to be inserted.
   * @return A {@code Single} holding the id of the attempt that was inserted.
   */
  @Insert
  Single<Long> insert(ChallengeAttempt attempt);

  /**
   * Inserts multiple attempts into the database.
   *
   * @param attempts The attempts to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of attempts that were inserted.
   */
  @Insert
  Single<List<Long>> insert(ChallengeAttempt... attempts);

  /**
   * Inserts multiple attempts into the database.
   *
   * @param attempts The attempts to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of attempts that were inserted.
   */
  @Insert
  Single<List<Long>> insert(Collection<ChallengeAttempt> attempts);

  /**
   * Updates a single attempt in the database.
   *
   * @param attempt The attempt to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(ChallengeAttempt attempt);

  /**
   * Updates multiple attempts in the database.
   *
   * @param attempts The attempts to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(ChallengeAttempt... attempts);

  /**
   * Updates multiple attempts in the database.
   *
   * @param attempts The attempts to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Collection<ChallengeAttempt> attempts);

  /**
   * Deletes a single attempt from the database.
   *
   * @param attempt The attempt to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(ChallengeAttempt attempt);

  /**
   * Deletes multiple attempts from the database.
   *
   * @param attempts The attempts to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(ChallengeAttempt... attempts);

  /**
   * Deletes multiple attempts from the database.
   *
   * @param attempts The attempts to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Collection<ChallengeAttempt> attempts);

  /**
   * Queries the database for a specific attempt, based on id.
   *
   * @param id The id of the attempt.
   * @return {@code LiveData} of the selected attempt.
   */
  @Query("SELECT * FROM ChallengeAttempt WHERE challenge_attempt_id = :id")
  LiveData<ChallengeAttempt> select(long id);

  /**
   * Queries the database for all attempts. Orders by most recent first.
   *
   * @return {@code LiveData} of a {@code List} of attempts.
   */
  @Query("SELECT * FROM CHALLENGEATTEMPT ORDER BY timestamp DESC")
  LiveData<List<ChallengeAttempt>> selectAll();

  /**
   * Queries the database for all attempts made by a specific player. Orders by most recent first.
   *
   * @param id The player's id.
   * @return {@code LiveData} of a {@code List} of attempts made by the specified player.
   */
  @Query("SELECT * FROM ChallengeAttempt WHERE player_id = :id ORDER BY timestamp DESC")
  LiveData<List<ChallengeAttempt>> selectAllWithPlayer(long id);

  /**
   * Queries the database for the attempts with the highest scores.
   *
   * @param numScores The number of scores to return.
   * @return {@code LiveData} of a {@code List} of high-scoring attempts.
   */
  @Transaction
  @Query("SELECT * FROM ChallengeAttempt ORDER BY total_score DESC LIMIT :numScores")
  LiveData<List<ChallengeAttemptWithPlayer>> selectHighScores(int numScores);

  /**
   * Queries the database for the attempts with the highest scores made by a specific player.
   *
   * @param id        The player's id.
   * @param numScores The number of scores to return.
   * @return {@code LiveData} of a {@code List} of high-scoring attempts by the specified player.
   */
  @Query("SELECT * FROM ChallengeAttempt WHERE player_id = :id "
      + "ORDER BY total_score DESC LIMIT :numScores")
  LiveData<List<ChallengeAttempt>> selectPlayerHighScores(long id, int numScores);

}
