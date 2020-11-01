package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public long getScaleId() {
    return scaleId;
  }

  public void setScaleId(long scaleId) {
    this.scaleId = scaleId;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  public int getCorrectCoins() {
    return correctCoins;
  }

  public void setCorrectCoins(int correctCoins) {
    this.correctCoins = correctCoins;
  }

  public int getIncorrectCoins() {
    return incorrectCoins;
  }

  public void setIncorrectCoins(int incorrectCoins) {
    this.incorrectCoins = incorrectCoins;
  }
}
