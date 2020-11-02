package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ScaleDao {

  @Insert
  Single<Long> insert(Scale scale);

  @Insert
  Single<List<Long>> insert(Scale...scales);

  @Insert
  Single<List<Long>> insert(Collection<Scale> scales);

  @Update
  Single<Integer> update(Scale scale);

  @Update
  Single<Integer> update(Scale...scales);

  @Update
  Single<Integer> update(Collection<Scale> scales);

  @Delete
  Single<Integer> delete(Scale scale);

  @Delete
  Single<Integer> delete(Scale...scales);

  @Delete
  Single<Integer> delete(Collection<Scale> scales);

  @Query("SELECT * FROM Scale WHERE scale_id = :id")
  LiveData<Scale> select(long id);

  @Query("SELECT * FROM Scale WHERE difficulty = :difficulty")
  LiveData<Scale> select(int difficulty);
}
