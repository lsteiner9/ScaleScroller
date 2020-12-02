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
    int[] steps = mode.getSteps();
    Set<Integer> noteNumbers = new HashSet<>();
    for (int step : steps) {
      noteNumbers.add(step + tonicNumber);
    }
    Set<Note> notes = new HashSet<>();
    notes.add(tonic);
    for (Note note : Note.values()) {
      int number = note.getNumber();
      if (noteNumbers.contains(number)) {
        Note[] possibilities = letterNameMap.get(number);
        String tonicString = tonic.toString();
        String possibleString = possibilities[0].toString();
        if ((tonicString.contains("\u266f") && possibleString.contains("\u266d"))
            || (tonicString.contains("\u266d") && possibleString.contains("\u266f"))) {
          notes.add(possibilities[1]);
        } else {
          notes.add(possibilities[0]);
        }
      }
    }
    return notes.toArray(new Note[notes.size()]);
  }

}
