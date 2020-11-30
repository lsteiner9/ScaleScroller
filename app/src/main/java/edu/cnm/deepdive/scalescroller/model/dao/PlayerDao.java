package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * Provides an interface with methods that perform actions on the {@link Player} table of the database.
 */
@Dao
public interface PlayerDao {

  /**
   * Inserts a single player into the database.
   *
   * @param player The player to be inserted.
   * @return A {@code Single} holding the id of the player that was inserted.
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Player player);

  /**
   * Inserts multiple players into the database.
   *
   * @param players The players to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of the the players that were inserted.
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Player... players);

  /**
   * Inserts multiple players into the database.
   *
   * @param players The players to be inserted.
   * @return A {@code Single} holding a {@code List} of ids of the the players that were inserted.
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Player> players);

  /**
   * Updates a single player in the database.
   *
   * @param player The player to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Player player);

  /**
   * Updates multiple players in the database.
   *
   * @param players The players to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Player... players);

  /**
   * Updates multiple players in the database.
   *
   * @param players The players to be updated.
   * @return A {@code Single} holding the number of updates performed.
   */
  @Update
  Single<Integer> update(Collection<Player> players);

  /**
   * Deletes a single player from the database.
   *
   * @param player The player to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Player player);

  /**
   * Deletes multiple players from the database.
   *
   * @param players The players to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Player... players);

  /**
   * Deletes multiple players from the database.
   *
   * @param players The players to be deleted.
   * @return A {@code Single} holding the number of deletions performed.
   */
  @Delete
  Single<Integer> delete(Collection<Player> players);

  /**
   * Queries the database for a specific player, based on id.
   *
   * @param id The player's id.
   * @return {@code LiveData} of the selected player.
   */
  @Query("SELECT * FROM Player WHERE player_id = :id")
  LiveData<Player> select(long id);

  /**
   * Queries the database for all players.
   *
   * @return {@code LiveData} with a {@code List} of all players.
   */
  @Query("SELECT * FROM Player ORDER BY username ASC")
  LiveData<List<Player>> selectAll();

  /**
   * Queries the database for a specific player, based on OAuth key.
   *
   * @param oauth The player's OAuth key.
   * @return {@code LiveData} of the selected player.
   */
  @Query("SELECT * FROM Player WHERE oauth_key = :oauth")
  LiveData<Player> selectWithOauth(String oauth);

}
