package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * Holds data in the database for attempts of levels in Learn mode.
 */
@Entity(
    indices = {
        @Index(value = {"timestamp"}, unique = true)
    },
    foreignKeys = {
        @ForeignKey(
            entity = Player.class,
            childColumns = "player_id",
            parentColumns = "player_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = Scale.class,
            childColumns = "scale_id",
            parentColumns = "scale_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class LearnLevelAttempt {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "learn_level_attempt_id")
  private long id;

  @ColumnInfo(name = "player_id", index = true)
  private long playerId;

  @ColumnInfo(name = "scale_id", index = true)
  private long scaleId;

  @ColumnInfo(index = true)
  private int difficulty;

  @NonNull
  private Date timestamp = new Date();

  @ColumnInfo(name = "correct_coins")
  private int correctCoins;

  @ColumnInfo(name = "incorrect_coins")
  private int incorrectCoins;

  /**
   * Returns the auto-generated id for the Learn level attempt.
   *
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the auto-generated id for the Learn level attempt.
   *
   * @param id The id to be set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the player id associated with the Learn level attempt.
   *
   * @return
   */
  public long getPlayerId() {
    return playerId;
  }

  /**
   * Sets the player id associated with the Learn level attempt.
   *
   * @param playerId The player id to be set.
   */
  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  /**
   * Returns the id for the attempted scale.
   *
   * @return
   */
  public long getScaleId() {
    return scaleId;
  }

  /**
   * Sets the id for the attempted scale.
   *
   * @param scaleId The id of the scale to be set.
   */
  public void setScaleId(long scaleId) {
    this.scaleId = scaleId;
  }

  /**
   * Returns the difficulty of the attempted scale.
   *
   * @return
   */
  public int getDifficulty() {
    return difficulty;
  }

  /**
   * Sets the difficulty of the attempted scale.
   *
   * @param difficulty The difficulty of the the attempted scale.
   */
  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Returns the timestamp when the attempt was completed/saved to the database.
   *
   * @return
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp when the attempt was completed/saved to the database.
   *
   * @param timestamp The timestamp when the attempt was completed/saved.
   */
  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Returns the number of correct coins collected.
   *
   * @return
   */
  public int getCorrectCoins() {
    return correctCoins;
  }

  /**
   * Sets the number of correct coins collected.
   *
   * @param correctCoins The number of correct coins collected.
   */
  public void setCorrectCoins(int correctCoins) {
    this.correctCoins = correctCoins;
  }

  /**
   * Returns the number of incorrect coins collected.
   *
   * @return
   */
  public int getIncorrectCoins() {
    return incorrectCoins;
  }

  /**
   * Sets the number of incorrect coins collected.
   *
   * @param incorrectCoins The number of incorrect coins collected.
   */
  public void setIncorrectCoins(int incorrectCoins) {
    this.incorrectCoins = incorrectCoins;
  }
}
