package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * Holds data in the database for attempts of levels in Challenge mode.
 */
@SuppressWarnings("NotNullFieldNotInitialized")
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
        )
    }
)
public class ChallengeAttempt {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "challenge_attempt_id")
  private long id;

  @ColumnInfo(name = "player_id", index = true)
  private long playerId;

  @ColumnInfo(name = "total_score", index = true)
  private int totalScore;

  @NonNull
  private Date timestamp = new Date();

  @ColumnInfo(name = "correct_coins")
  private int correctCoins;

  @ColumnInfo(name = "incorrect_coins")
  private int incorrectCoins;

  @ColumnInfo(name = "last_scale_id", index = true)
  private long lastScaleId;

  /**
   * Returns the auto-generated id for the Challenge level attempt.
   *
   * @return The id of the Challenge attempt.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the auto-generated id for the Challenge level attempt.
   *
   * @param id The id to be set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the player id associated with the Challenge level attempt.
   *
   * @return Returns the player's id.
   */
  public long getPlayerId() {
    return playerId;
  }

  /**
   * Sets the player id associated with the Challenge level attempt.
   *
   * @param playerId The player id to be set.
   */
  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  /**
   * Returns the total score the player achieved in the Challenge level attempt.
   *
   * @return The total score achieved.
   */
  public int getTotalScore() {
    return totalScore;
  }

  /**
   * Sets the total score the player achieved in the Challenge level attempt.
   *
   * @param totalScore The total score achieved.
   */
  public void setTotalScore(int totalScore) {
    this.totalScore = totalScore;
  }

  /**
   * Returns the timestamp when the attempt was completed/saved to the database.
   *
   * @return The timestamp.
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
   * @return The total correct coins.
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
   * @return The total incorrect coins.
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

  /**
   * Returns the id of the last scale attempted (i.e. the scale that the player lost their last life
   * on).
   *
   * @return The id of the last scale attempted.
   */
  public long getLastScaleId() {
    return lastScaleId;
  }

  /**
   * Sets the id of the last scale attempted (i.e. the scale that the player lost their last life
   * on).
   *
   * @param lastScaleId The id of the last scale attempted.
   */
  public void setLastScaleId(long lastScaleId) {
    this.lastScaleId = lastScaleId;
  }
}
