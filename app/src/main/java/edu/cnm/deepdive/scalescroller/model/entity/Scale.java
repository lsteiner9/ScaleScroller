package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Holds data in the database for available scales to be used. This includes enumerated types from
 * the {@link Note} and {@link Mode} enums.
 */
@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(
    indices = {
        @Index(value = {"tonic", "mode"}, unique = true)
    }
)
public class Scale {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "scale_id")
  private long id;

  @NonNull
  @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
  private Note tonic;

  @NonNull
  @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
  private Mode mode;

  /**
   * Returns the auto-generated id for the scale.
   *
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the auto-generated id for the scale.
   *
   * @param id The id for the scale.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the tonic of the scale: an enumerated type from the {@code Note} enum.
   *
   * @return
   */
  @NonNull
  public Note getTonic() {
    return tonic;
  }

  /**
   * Sets the tonic of the scale: an enumerated type from the {@code Note} enum.
   *
   * @param tonic The tonic of the scale.
   */
  public void setTonic(@NonNull Note tonic) {
    this.tonic = tonic;
  }

  /**
   * Returns the mode of the scale (major, natural minor, etc.): an enumerated type from the {@code
   * Mode} enum.
   *
   * @return
   */
  @NonNull
  public Mode getMode() {
    return mode;
  }

  /**
   * Sets the mode of the scale (major, natural minor, etc.): an enumerated type from the {@code
   * Mode} enum.
   *
   * @param mode The mode of the scale.
   */
  public void setMode(@NonNull Mode mode) {
    this.mode = mode;
  }

}
