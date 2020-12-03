package edu.cnm.deepdive.scalescroller.model;

import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The {@code Level} class contains game logic used in conjunction with the {@link
 * edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel}.
 */
public class Level {

  private static final int MIN_PASSING_SCORE = R.integer.min_passing_score;
  private int score;
  private int hearts;
  private final Scale scale;
  private Boolean levelWon;

  /**
   * The constructor initializes the scale used in the level, and creates a list of the correct
   * notes from the scale.
   *
   * @param scale The scale used.
   */
  public Level(Scale scale) {
    this.scale = scale;
  }

  // TODO Put logic here for each level in the game?

  /**
   * Returns the current score in the level.
   *
   * @return The current score.
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the score for the level by adding the new points. Also sets levelWon to true if the
   * minimum score has been reached.
   *
   * @param points The points to be added to the current score.
   */
  public void setScore(int points) {
    score += points;
    if (score >= MIN_PASSING_SCORE) {
      levelWon = true;
    }
  }

  /**
   * Returns the current number of lives/hearts left.
   *
   * @return The hearts left.
   */
  public int getHearts() {
    return hearts;
  }

  /**
   * Increments or decrements the number of hearts left. Also sets levelWon to false if there are no
   * hearts left.
   *
   * @param heartAdded True if a heart has been added, false if it has been removed.
   */
  public void setHearts(boolean heartAdded) {
    if (heartAdded) {
      hearts++;
    } else {
      hearts--;
    }
    if (hearts == 0) {
      levelWon = false;
    }
  }

  /**
   * Returns the scale associated with the level.
   *
   * @return The level's scale.
   */
  public Scale getScale() {
    return scale;
  }

  /**
   * Returns true if the level has been won and false if it has been lost. If the level is in
   * progress, this will return null.
   *
   * @return Whether the level has been won, lost, or is in progress.
   */
  public Boolean getLevelWon() {
    return levelWon;
  }

  /**
   * Returns the notes not associated with the scale.
   *
   * @return An array of {@link Note} that do not belong in the scale.
   */
  public Note[] getIncorrectNotes() {
    // TODO stream all note values and filter based whether they are not in correctNotes
    return null;
  }

}
