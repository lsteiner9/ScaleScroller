package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    indices = {
        @Index(value = {"scale_name"}, unique = true),
        @Index(value = {"difficulty"}, unique = true)
    }
)
public class Scale {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "scale_id")
  private long id;

  @SuppressWarnings("NotNullFieldNotInitialized")
  @NonNull
  @ColumnInfo(name = "scale_name")
  private LetterName scaleName;

  private int difficulty;

  /*This int will represent the scale degrees by bit:
    32 bits:
      7 for white key note names: CDEFGAB
      + 7 for sharp notes C#D#E#F#G#A#B#
      + 7 for flat notes CbDbEbFbGbAbBb
      + 3 bits for unusual note names: FxCxGx
      + 8 empty (unused) bits
   */
  private int degrees;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public LetterName getScaleName() {
    return scaleName;
  }

  public void setScaleName(@NonNull LetterName scaleName) {
    this.scaleName = scaleName;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public int getDegrees() {
    return degrees;
  }

  public void setDegrees(int degrees) {
    this.degrees = degrees;
  }

  public enum LetterName {
    C_MAJOR, C_SHARP_MAJOR, D_MAJOR, E_FLAT_MAJOR, E_MAJOR, F_MAJOR,
    F_SHARP_MAJOR, G_MAJOR, A_FLAT_MAJOR, A_MAJOR, B_FLAT_MAJOR, B_MAJOR,

    C_MINOR, C_SHARP_MINOR, D_MINOR, E_FLAT_MINOR, E_MINOR, F_MINOR,
    F_SHARP_MINOR, G_MINOR, A_FLAT_MINOR, A_MINOR, B_FLAT_MINOR, B_MINOR,

    C_MINOR_HAR, C_SHARP_MINOR_HAR, D_MINOR_HAR, E_FLAT_MINOR_HAR,
    E_MINOR_HAR, F_MINOR_HAR, F_SHARP_MINOR_HAR, G_MINOR_HAR,
    A_FLAT_MINOR_HAR, A_MINOR_HAR, B_FLAT_MINOR_HAR, B_MINOR_HAR,

    C_MINOR_MEL, C_SHARP_MINOR_MEL, D_MINOR_MEL, E_FLAT_MINOR_MEL,
    E_MINOR_MEL, F_MINOR_MEL, F_SHARP_MINOR_MEL, G_MINOR_MEL,
    A_FLAT_MINOR_MEL, A_MINOR_MEL, B_FLAT_MINOR_MEL, B_MINOR_MEL,

    USER_SUBMITTED;

    public int startingNote(LetterName scaleName) {
      return scaleName.ordinal() % 12;
    }

    public static Integer letterNameToInteger(LetterName scale) {
      return (scale != null)? scale.ordinal() : null;
    }

    public static LetterName integerToLetterName(Integer value) {
      return (value != null)? LetterName.values()[value] : null;
    }
  }
}
