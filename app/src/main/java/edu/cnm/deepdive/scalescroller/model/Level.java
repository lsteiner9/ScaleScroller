package edu.cnm.deepdive.scalescroller.model;

import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO javadoc
public class Level {

  private int score;
  private int hearts;
  private int speed;
  private final Scale scale;
  private Note[] correctNotes;

  public Level(Scale scale) {
    this.scale = scale;
    correctNotes = getNotes();
  }

  // TODO Put logic here for each level in the game

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getHearts() {
    return hearts;
  }

  public void setHearts(int hearts) {
    this.hearts = hearts;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public Scale getScale() {
    return scale;
  }

  public Note[] getCorrectNotes() {
    return correctNotes;
  }

  public String getNotesString() {
    return Arrays.toString(correctNotes);
  }

  //THIS IS A MESSSSSS (and probably still doesn't work quite right)
  private Note[] getNotes() {
    Map<Integer, Note[]> letterNameMap = Note.getNoteMap();
    Mode mode = scale.getMode();
    Note tonic = scale.getTonic();
    int tonicNumber = tonic.getNumber();
    byte[] steps = mode.getSteps();
    Set<Integer> noteNumbers = new HashSet<>();
    int length = (mode == Mode.MELODIC_MINOR) ? 9 : 7;
    Set<Note> notes = new HashSet<>();
    notes.add(tonic);
    for (int i = 1; i < length; i++) {
      noteNumbers.add(tonicNumber + steps[i - 1]);
    }
    for (Note note : Note.values()) {
      int number = note.getNumber();
      if (noteNumbers.contains(number)) {
        Note[] possibilities = letterNameMap.get(number);
        if (tonic.toString().contains("#") && possibilities[0].toString().contains("b")) {
          notes.add(possibilities[1]);
        } else if (tonic.toString().contains("b") && possibilities[0].toString().contains("#")) {
          notes.add(possibilities[1]);
        } else {
          notes.add(possibilities[0]);
        }
      }
    }
    return notes.toArray(new Note[notes.size()]);
  }

}
