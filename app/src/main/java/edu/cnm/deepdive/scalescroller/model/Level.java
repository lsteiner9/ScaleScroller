package edu.cnm.deepdive.scalescroller.model;

import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import java.util.Random;

// TODO javadoc
public class Level {

  private int score;
  private int hearts;
  private int speed;
  private final Scale scale;
  private Note[] correctNotes;

  public Level(Scale scale) {
    this.scale = scale;
    correctNotes = getNotes(scale);
  }

  private Note[] getNotes(Scale scale) {
    Mode mode = scale.getMode();
    Note tonic = scale.getTonic();
    int tonicNumber = tonic.getNumber();
    byte[] steps = mode.getSteps();
    int[] noteNumbers = new int[(mode == Mode.MELODIC_MINOR) ? 9 : 7];
    Note[] notes = new Note[noteNumbers.length];
    notes[0] = tonic;
    for (int i = 1; i < noteNumbers.length; i++) {
      noteNumbers[i] = tonicNumber + steps[i - 1];
    }
    // TODO get the corresponding note for each noteNumber
    // This requires getting all possible notes, then figuring out which one
    // makes more sense given the tonic...
    // maybe having the sharpkey boolean actually makes sense here?
    for (int i = 0; i < noteNumbers.length; i++) {
      // ???
    }

    return notes;
  }

  // TODO Put logic here for each level in the game
  public boolean play() {
    //this is just a placeholder
    int startingScore = score;
    while (hearts > 0 || score - startingScore > 100) {
      score += new Random().nextInt(200);
      hearts--;
    }
    return (hearts > 0);
  }

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

}
