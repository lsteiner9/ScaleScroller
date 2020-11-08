package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface PlayerDao {

  @Insert
  Single<Long> insert(Player player);

  @Insert
  Single<List<Long>> insert(Player...players);

  @Insert
  Single<List<Long>> insert(Collection<Player> players);

  @Update
  Single<Integer> update(Player player);

  @Update
  Single<Integer> update(Player...players);

  @Update
  Single<Integer> update(Collection<Player> players);

  @Delete
  Single<Integer> delete(Player player);

  @Delete
  Single<Integer> delete(Player...players);

  @Delete
  Single<Integer> delete(Collection<Player> players);

  @Query("SELECT * FROM Player ORDER BY username ASC")
  LiveData<List<Player>> selectAll();

  @Query("SELECT * FROM Player WHERE player_id = :id")
  LiveData<Player> select(long id);

  @Query("SELECT * FROM Player WHERE oauth_key = :oauth")
  LiveData<Player> selectWithOauth(long oauth);

}
