package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

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

  //actually do I even need this? difficulty and degrees can be gotten from the lettername enum, right?
  // so maybe just getters are needed?
  /*This int will represent the scale degrees by bit:
      7 for white key note names: CDEFGAB
      + 7 for sharp notes C#D#E#F#G#A#B#
      + 7 for flat notes CbDbEbFbGbAbBb
      + 3 bits for unusual note names: FxCxGx
      + 8 empty (unused) bits
    So, for example, the int -33554432 (0b1111111_0000000_0000000_000_00000000)
    signifies C Major; the int 64488448 (0b0000001_1110110_0000000_100_00000000)
    signifies g# harmonic minor; the int -805062656 (0b1101000_0000000_1110111_000_00000000)
    signifies eb melodic minor.
    It might be better to have the empty bits at the beginning for smaller, more manageable numbers?
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
    C_FLAT_MAJOR (5, 14), //placeholder scaleDegrees numbers, but correct difficulties
    C_MAJOR (5, 0),
    C_SHARP_MAJOR (5, 13),
    D_FLAT_MAJOR (5, 10),
    D_MAJOR (5, 3),
    E_FLAT_MAJOR (5, 6),
    E_MAJOR (5, 7),
    F_MAJOR (5, 2),
    F_SHARP_MAJOR (5, 11),
    G_FLAT_MAJOR (5, 12),
    G_MAJOR (5, 1),
    A_FLAT_MAJOR (5, 8),
    A_MAJOR (5, 5),
    B_FLAT_MAJOR (5, 4),
    B_MAJOR (5, 9),
    // 0-14

    C_NATURAL_MINOR (5, 21),
    C_SHARP_NATURAL_MINOR (5, 22),
    D_NATURAL_MINOR (5, 17),
    D_SHARP_NATURAL_MINOR (5, 27),
    E_FLAT_NATURAL_MINOR (5, 26),
    E_NATURAL_MINOR (5, 16),
    F_NATURAL_MINOR (5, 23),
    F_SHARP_NATURAL_MINOR (5, 20),
    G_NATURAL_MINOR (5, 19),
    G_SHARP_NATURAL_MINOR (5, 24),
    A_FLAT_NATURAL_MINOR (5, 29),
    A_NATURAL_MINOR (5, 15),
    A_SHARP_NATURAL_MINOR (5, 28),
    B_FLAT_NATURAL_MINOR (5, 25),
    B_NATURAL_MINOR (5, 18),
    // 15-29

    C_HARMONIC_MINOR (5, 36),
    C_SHARP_HARMONIC_MINOR (5, 37),
    D_HARMONIC_MINOR (5, 32),
    D_SHARP_HARMONIC_MINOR (5, 42),
    E_FLAT_HARMONIC_MINOR (5, 41),
    E_HARMONIC_MINOR (5, 31),
    F_HARMONIC_MINOR (5, 38),
    F_SHARP_HARMONIC_MINOR (5, 35),
    G_HARMONIC_MINOR (5, 34),
    G_SHARP_HARMONIC_MINOR (5, 39),
    A_FLAT_HARMONIC_MINOR (5, 44),
    A_HARMONIC_MINOR (5, 30),
    A_SHARP_HARMONIC_MINOR (5, 43),
    B_FLAT_HARMONIC_MINOR (5, 40),
    B_HARMONIC_MINOR (5, 33),
    // 30-44

    C_MELODIC_MINOR (5, 51),
    C_SHARP_MELODIC_MINOR (5, 52),
    D_MELODIC_MINOR (5, 47),
    D_SHARP_MELODIC_MINOR (5, 56),
    E_FLAT_MELODIC_MINOR (5, 57),
    E_MELODIC_MINOR (5, 46),
    F_MELODIC_MINOR (5, 53),
    F_SHARP_MELODIC_MINOR (5, 50),
    G_MELODIC_MINOR (5, 49),
    G_SHARP_MELODIC_MINOR (5, 54),
    A_FLAT_MELODIC_MINOR (5, 59),
    A_MELODIC_MINOR (5, 45),
    A_SHARP_MELODIC_MINOR (5, 58),
    B_FLAT_MELODIC_MINOR (5, 55),
    B_MELODIC_MINOR (5, 48);
    // 45-59

    private final int scaleDegrees;
    private final int difficulty;

    LetterName(int scaleDegrees, int difficulty) {
      this.scaleDegrees = scaleDegrees;
      this.difficulty = difficulty;
    }

    public int startingNote(LetterName scaleName) {
      return scaleName.ordinal() % 12;
    }

    public int getScaleDegrees() {
      return scaleDegrees;
    }

    public int getDifficulty() {
      return difficulty;
    }

    @TypeConverter
    public static Integer letterNameToInteger(LetterName scale) {
      return (scale != null)? scale.ordinal() : null;
    }

    @TypeConverter
    public static LetterName integerToLetterName(Integer value) {
      return (value != null)? LetterName.values()[value] : null;
    }

    @NonNull
    @Override
    public String toString() {
      return super.toString()
          .toLowerCase()
          .replace('_', ' ')
          .replace("sharp", "#")
          .replace("flat", "b");
    }
  }
}
