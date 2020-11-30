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

/**
 * Provides an interface with methods that perform actions on the {@link LearnLevelAttempt} table of
 * the database.
 */
@Dao
public interface LearnLevelAttemptDao {

  /**
   * Inserts a single attempt into the database.
   *
   * @param attempt The attempt to be inserted.
   * @return A {@code Single} holding the id of the attempt that was inserted.
   */
  @Insert
  Single<Long> insert(LearnLevelAttempt attempt);

  /**
   * Inserts multiple attempts into the database.
   *
   * @param attempts The attempts to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of attempts that were inserted.
   */
  @Insert
  Single<List<Long>> insert(LearnLevelAttempt... attempts);

  /**
   * Inserts multiple attempts into the database.
   *
   * @param attempts The attempts to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of attempts that were inserted.
   */
  @Insert
  Single<List<Long>> insert(Collection<LearnLevelAttempt> attempts);

  /**
   * Updates a single attempt in the database.
   *
   * @param attempt The attempt to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(LearnLevelAttempt attempt);


  /**
   * Updates multiple attempts in the database.
   *
   * @param attempts The attempts to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(LearnLevelAttempt... attempts);

  /**
   * Updates multiple attempts in the database.
   *
   * @param attempts The attempts to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Collection<LearnLevelAttempt> attempts);

  /**
   * Deletes a single attempt from the database.
   *
   * @param attempt The attempt to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(LearnLevelAttempt attempt);

  /**
   * Deletes multiple attempts from the database.
   *
   * @param attempts The attempts to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(LearnLevelAttempt... attempts);

  /**
   * Deletes multiple attempts from the database.
   *
   * @param attempts The attempts to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Collection<LearnLevelAttempt> attempts);

  /**
   * Queries the database for a specific attempt, based on id.
   *
   * @param id The id of the attempt.
   * @return {@code LiveData} of the selected attempt.
   */
  @Query("SELECT * FROM LearnLevelAttempt WHERE learn_level_attempt_id = :id")
  LiveData<LearnLevelAttempt> select(long id);

  /**
   * Queries the database for all attempts.
   *
   * @return {@code LiveData} of a {@code List} of attempts.
   */
  @Query("SELECT * FROM LearnLevelAttempt")
  LiveData<List<LearnLevelAttempt>> selectAll();

  /**
   * Queries the database for all attempts made by a specific player.
   *
   * @param id The player's id.
   * @return {@code LiveData} of a {@code List} of attempts made by the specified player.
   */
  @Query("SELECT * FROM LearnLevelAttempt WHERE player_id = :id")
  LiveData<List<LearnLevelAttempt>> selectAllWithPlayer(long id);

  /**
   * Queries the database for the attempt that had the highest difficulty rating for a specific
   * player.
   *
   * @param id The player's id.
   * @return {@code LiveData} of the attempt with the highest difficulty rating for the specified
   * player.
   */
  @Query("SELECT * FROM LearnLevelAttempt WHERE player_id = :id "
      + "ORDER BY difficulty DESC LIMIT 1")
  LiveData<LearnLevelAttempt> selectHighestDifficultyWithPlayer(long id);
}
