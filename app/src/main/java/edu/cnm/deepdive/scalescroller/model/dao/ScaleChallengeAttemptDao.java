package edu.cnm.deepdive.scalescroller.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.scalescroller.model.entity.ScaleChallengeAttempt;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ScaleChallengeAttemptDao {

  @Insert
  Single<Long> insert(ScaleChallengeAttempt attempt);

  @Insert
  Single<List<Long>> insert(ScaleChallengeAttempt...attempts);

  @Insert
  Single<List<Long>> insert(Collection<ScaleChallengeAttempt> attempts);

  @Delete
  Single<Integer> delete(ScaleChallengeAttempt attempt);

  @Delete
  Single<Integer> delete(ScaleChallengeAttempt... attempts);

  @Delete
  Single<Integer> delete(Collection<ScaleChallengeAttempt> attempts);

  //I think I need to use a pojo or two here to select a list of scales
  // and a list of challenge attempts
//  @Query("SELECT")
//  LiveData<ScaleWithChallengeAttempt> select()
//  @Query("SELECT")
//  LiveData<ChallengeAttemptWith> select()
}
