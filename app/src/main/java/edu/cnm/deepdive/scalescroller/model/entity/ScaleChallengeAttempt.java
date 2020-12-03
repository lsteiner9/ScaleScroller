package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * An associative entity that aids in the many-to-many relationship between {@link ChallengeAttempt}
 * and {@link Scale}. Also holds a timestamp for each particular {@code Scale} within the {@code
 * ChallengeAttempt}.
 */
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

  /**
   * Returns the auto-generated id for the associative entity.
   *
   * @return The id.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the auto-generated id for the associative entity.
   *
   * @param id The id to be set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the id of the {@code ChallengeAttempt} foreign key.
   *
   * @return The id of the challenge attempt.
   */
  public long getAttemptId() {
    return attemptId;
  }

  /**
   * Sets the id of the {@code ChallengeAttempt} foreign key.
   *
   * @param attemptId The id of the {@code ChallengeAttempt} foreign key.
   */
  public void setAttemptId(long attemptId) {
    this.attemptId = attemptId;
  }

  /**
   * Returns the id of the {@code Scale} foreign key.
   *
   * @return The id of the scale.
   */
  public long getScaleId() {
    return scaleId;
  }

  /**
   * Sets the id of the {@code Scale} foreign key.
   *
   * @param scaleId The id of the {@code Scale} foreign key.
   */
  public void setScaleId(long scaleId) {
    this.scaleId = scaleId;
  }

  /**
   * Returns the timestamp when the associative entity was created.
   *
   * @return The timestamp.
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp when the associative entity was created.
   *
   * @param timestamp The timestamp when this entity was created.
   */
  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }
}
