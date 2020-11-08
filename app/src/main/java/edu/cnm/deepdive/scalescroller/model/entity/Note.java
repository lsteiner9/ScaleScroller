package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import java.util.Arrays;

public enum Note {

  C_FLAT(true, 11),
  C(true, 0),
  C_SHARP(true, 1),
  C_DOUBLE_SHARP(false, 2),
  D_FLAT(true, 1),
  D(true, 2),
  D_SHARP(true, 3),
  E_FLAT(true, 3),
  E(true, 4),
  E_SHARP(false, 5),
  F_FLAT(false, 4),
  F(true, 5),
  F_SHARP(true, 6),
  F_DOUBLE_SHARP(false, 7),
  G_FLAT(true, 6),
  G(true, 7),
  G_SHARP(true, 8),
  G_DOUBLE_SHARP(false, 9),
  A_FLAT(true, 8),
  A(true, 9),
  A_SHARP(true, 10),
  B_FLAT(true, 10),
  B(true, 11),
  B_SHARP(false, 12);

  private final boolean tonic;
  private final int number;


  Note(boolean tonic, int number) {
    this.tonic = tonic;
    this.number = number;
  }

  public static Note[] tonics() {
    return Arrays.stream(Note.values())
        .filter(Note::isTonic)
        .toArray(Note[]::new);
  }

  public boolean isTonic() {
    return tonic;
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
