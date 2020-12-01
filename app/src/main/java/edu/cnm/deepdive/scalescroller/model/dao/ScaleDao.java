package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * Provides an interface with methods that perform actions on the {@link Scale} table of the
 * database.
 */
@Dao
public interface ScaleDao {

  /**
   * Inserts a single scale into the database.
   *
   * @param scale The scale to be inserted.
   * @return A {@code Single} holding the id of the scale that was inserted.
   */
  @Insert
  Single<Long> insert(Scale scale);

  /**
   * Inserts multiple scales into the database.
   *
   * @param scales The scales to be inserted.
   * @return A {@code Single} holding {@code List} of ids of scales that were inserted.
   */
  @Insert
  Single<List<Long>> insert(Scale... scales);

  /**
   * Inserts multiple scales into the database.
   *
   * @param scales The scales to be inserted.
   * @return A {@code Single} holding {@code List} of ids of scales that were inserted.
   */
  @Insert
  Single<List<Long>> insert(Collection<Scale> scales);

  /**
   * Updates a single scale in the database.
   *
   * @param scale The scale to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Scale scale);

  /**
   * Updates multiple scales in the database.
   *
   * @param scales The scales to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Scale... scales);

  /**
   * Updates multiple scales in the database.
   *
   * @param scales The scales to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Collection<Scale> scales);

  /**
   * Deletes a single scale from the database.
   *
   * @param scale The scale to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Scale scale);

  /**
   * Deletes multiple scales from the database.
   *
   * @param scales The scales to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Scale... scales);

  /**
   * Deletes multiple scales from the database.
   *
   * @param scales The scales to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Collection<Scale> scales);

  /**
   * Queries the database for a specific scale, based on id.
   *
   * @param id The scale's id.
   * @return {@code LiveData} of the selected scale.
   */
  @Query("SELECT * FROM Scale WHERE scale_id = :id")
  LiveData<Scale> select(long id);

  /**
   * Queries the database for all scales.
   *
   * @return {@code LiveData} of a {@code List} of scales.
   */
  @Query("SELECT * FROM Scale")
  LiveData<List<Scale>> selectAll();

  // TODO add javadoc
  @Query("SELECT * FROM Scale ORDER BY difficulty ASC")
  LiveData<List<Scale>> selectAllOrderByDifficulty();

  /**
   * Queries the database for a specific scale, based on tonic and mode.
   *
   * @param mode  The scale's mode.
   * @param tonic The scale's tonic.
   * @return {@code LiveData} of the selected scale.
   */
  @Query("SELECT * FROM Scale WHERE mode = :mode AND tonic = :tonic")
  LiveData<Scale> selectWithScaleName(Mode mode, Note tonic);

  /**
   * Queries the database for specific scales, based on tonic.
   *
   * @param tonic The tonic of the scales.
   * @return {@code LiveData} of a {@code List} of scales.
   */
  @Query("SELECT * FROM Scale WHERE tonic = :tonic")
  LiveData<List<Scale>> selectWithTonic(Note tonic);

  /**
   * Queries the database for specific scales, based on mode.
   *
   * @param mode The mode of the scales.
   * @return {@code LiveData} of a {@code List} of scales.
   */
  @Query("SELECT * FROM Scale WHERE mode = :mode")
  LiveData<List<Scale>> selectWithMode(Mode mode);
}
