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
    @Index(value = {"difficulty"}, unique = false),
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
  private Long id;

  @NonNull
  @ColumnInfo(name = "player_id")
  private Long playerId;

  @NonNull
  @ColumnInfo(name = "scale_id")
  private Long scaleId;

  private int difficulty;

  @NonNull
  private Date timestamp = new Date();

  private int correctCoins;

  private int incorrectCoins;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public Long getScaleId() {
    return scaleId;
  }

  public void setScaleId(Long scaleId) {
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
