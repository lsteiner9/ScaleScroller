package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
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
  private Long id;

  @ColumnInfo(name = "challenge_attempt_id")
  private Long attemptId;

  @ColumnInfo(name = "scale_id")
  private Long scaleId;

  public Long getId() {
    return id;
  }

  public Long getAttemptId() {
    return attemptId;
  }

  public void setAttemptId(Long attemptId) {
    this.attemptId = attemptId;
  }

  public Long getScaleId() {
    return scaleId;
  }

  public void setScaleId(Long scaleId) {
    this.scaleId = scaleId;
  }
}
