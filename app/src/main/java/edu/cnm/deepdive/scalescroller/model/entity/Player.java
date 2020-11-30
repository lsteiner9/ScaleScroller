package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Holds data in the database for players of ScaleScroller.
 */
@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(
    indices = {
        @Index(value = {"oauth_key"}, unique = true),
        @Index(value = {"username"}, unique = true)
    }
)
public class Player {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "player_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "oauth_key")
  private String oauthKey;

  @NonNull
  private String username;

  @ColumnInfo(name = "highest_learn_level")
  private int highestLearnLevel;

  /**
   * Returns the auto-generated id for the player.
   *
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the auto-generated id for the player.
   *
   * @param id The id to be set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the OAuth key for the player.
   *
   * @return
   */
  @NonNull
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Sets the OAuth key for the player.
   *
   * @param oauthKey The OAuth key to be set.
   */
  public void setOauthKey(@NonNull String oauthKey) {
    this.oauthKey = oauthKey;
  }

  /**
   * Returns the player's username.
   *
   * @return
   */
  @NonNull
  public String getUsername() {
    return username;
  }

  /**
   * Sets the player's username.
   *
   * @param username The username to be set.
   */
  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  /**
   * Returns the highest learn level difficulty that the player has completed.
   *
   * @return
   */
  public int getHighestLearnLevel() {
    return highestLearnLevel;
  }

  /**
   * Sets the highest learn level difficulty that the player has completed.
   *
   * @param highestLearnLevel The highest learn level difficulty that the player has completed.
   */
  public void setHighestLearnLevel(int highestLearnLevel) {
    this.highestLearnLevel = highestLearnLevel;
  }
}
