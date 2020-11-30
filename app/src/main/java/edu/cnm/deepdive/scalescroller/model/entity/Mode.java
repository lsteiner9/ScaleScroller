package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumerates the different modes available for use in ScaleScroller. Each enumerated type includes
 * the notes, measured in half-steps from the tonic, and a difficulty rating to help structure
 * gameplay.
 */
public enum Mode {

  MAJOR(new byte[]{2, 4, 5, 7, 9, 11}) {
    @Override
    public Map<Note, Integer> getDifficulty() {
      Map<Note, Integer> map = new HashMap<>();
      map.put(Note.C, 0);
      map.put(Note.G, 1);
      map.put(Note.F, 2);
      map.put(Note.D, 3);
      map.put(Note.B_FLAT, 4);
      map.put(Note.A, 5);
      map.put(Note.E_FLAT, 6);
      map.put(Note.E, 7);
      map.put(Note.A_FLAT, 8);
      map.put(Note.B, 9);
      map.put(Note.D_FLAT, 10);
      map.put(Note.F_SHARP, 11);
      map.put(Note.G_FLAT, 12);
      map.put(Note.C_SHARP, 13);
      map.put(Note.C_FLAT, 14);
      return map;
    }
  },
  NATURAL_MINOR(new byte[]{2, 3, 5, 7, 8, 10}) {
    @Override
    public Map<Note, Integer> getDifficulty() {
      Map<Note, Integer> map = new HashMap<>();
      map.put(Note.A, 15);
      map.put(Note.E, 16);
      map.put(Note.D, 17);
      map.put(Note.B, 18);
      map.put(Note.G, 19);
      map.put(Note.F_SHARP, 20);
      map.put(Note.C, 21);
      map.put(Note.C_SHARP, 22);
      map.put(Note.F, 23);
      map.put(Note.G_SHARP, 24);
      map.put(Note.B_FLAT, 25);
      map.put(Note.D_SHARP, 26);
      map.put(Note.E_FLAT, 27);
      map.put(Note.A_SHARP, 28);
      map.put(Note.A_FLAT, 29);
      return map;
    }
  },
  HARMONIC_MINOR(new byte[]{2, 3, 5, 7, 8, 11}) {
    @Override
    public Map<Note, Integer> getDifficulty() {
      Map<Note, Integer> map = new HashMap<>();
      map.put(Note.A, 30);
      map.put(Note.E, 31);
      map.put(Note.D, 32);
      map.put(Note.B, 33);
      map.put(Note.G, 34);
      map.put(Note.F_SHARP, 35);
      map.put(Note.C, 36);
      map.put(Note.C_SHARP, 37);
      map.put(Note.F, 38);
      map.put(Note.G_SHARP, 39);
      map.put(Note.B_FLAT, 40);
      map.put(Note.D_SHARP, 41);
      map.put(Note.E_FLAT, 42);
      map.put(Note.A_SHARP, 43);
      map.put(Note.A_FLAT, 44);
      return map;
    }
  },
  MELODIC_MINOR(new byte[]{2, 3, 5, 7, 8, 9, 10, 11}) {
    @Override
    public Map<Note, Integer> getDifficulty() {
      Map<Note, Integer> map = new HashMap<>();
      map.put(Note.A, 45);
      map.put(Note.E, 46);
      map.put(Note.D, 47);
      map.put(Note.B, 48);
      map.put(Note.G, 49);
      map.put(Note.F_SHARP, 50);
      map.put(Note.C, 51);
      map.put(Note.C_SHARP, 52);
      map.put(Note.F, 53);
      map.put(Note.G_SHARP, 54);
      map.put(Note.B_FLAT, 55);
      map.put(Note.D_SHARP, 56);
      map.put(Note.E_FLAT, 57);
      map.put(Note.A_SHARP, 58);
      map.put(Note.A_FLAT, 59);
      return map;
    }
  };

  private final byte[] steps;
  private final Map<Note, Integer> difficultyMap;

  /**
   * Constructor for the enum. Initializes the notes in the scale (in terms of half-steps above
   * tonic) and the difficulty map.
   *
   * @param steps The notes in the scale.
   */
  Mode(byte[] steps) {
    this.steps = steps;
    difficultyMap = getDifficulty();
  }

  /**
   * Returns a map of tonic notes to integer difficulties.
   *
   * @return
   */
  public abstract Map<Note, Integer> getDifficulty();

  /**
   * Returns the notes in the scale (in terms of half-steps above tonic).
   *
   * @return
   */
  public byte[] getSteps() {
    return steps;
  }

  /**
   * Overrides the toString method for a more user-readable scale mode representation.
   *
   * @return
   */
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
