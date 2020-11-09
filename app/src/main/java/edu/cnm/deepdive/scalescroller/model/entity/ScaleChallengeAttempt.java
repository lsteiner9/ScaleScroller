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
        @Index(value = {"challenge_attempt_id", "scale_id"}, unique = true)
    },
    foreignKeys = {
        @ForeignKey(
            entity = Scale.class,
            childColumns = "scale_id",
            parentColumns = "scale_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = ChallengeAttempt.class,
            childColumns = "challenge_attempt_id",
            parentColumns = "challenge_attempt_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class ScaleChallengeAttempt {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "scale_challenge_attempt_id")
  private long id;

  @ColumnInfo(name = "challenge_attempt_id", index = true)
  private long attemptId;

  @ColumnInfo(name = "scale_id", index = true)
  private long scaleId;

  @NonNull
  @ColumnInfo(index = true)
  private Date timestamp = new Date();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getAttemptId() {
    return attemptId;
  }

  public void setAttemptId(long attemptId) {
    this.attemptId = attemptId;
  }

  public long getScaleId() {
    return scaleId;
  }

  public void setScaleId(long scaleId) {
    this.scaleId = scaleId;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }
}
