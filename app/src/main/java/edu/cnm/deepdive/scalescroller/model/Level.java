package edu.cnm.deepdive.scalescroller.model;

import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.model.entity.Mode;
import edu.cnm.deepdive.scalescroller.model.entity.Note;

/**
 * The {@code Level} class contains game logic used in conjunction with the {@link
 * edu.cnm.deepdive.scalescroller.viewmodel.GameViewModel}.
 */
public class Level {

  private static final int MIN_PASSING_SCORE = R.integer.min_passing_score;
  private int score;
  private int hearts;
  private final Note tonic;
  private final Mode mode;
  private Boolean levelWon;

  /**
   * The constructor initializes the tonic and mode of the scale.
   *
   * @param tonic The scale's tonic.
   * @param mode  The scale's mode.
   */
  public Level(Note tonic, Mode mode) {
    this.tonic = tonic;
    this.mode = mode;
    score = 0;
    hearts = 3;
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
   * Returns the tonic of the scale.
   *
   * @return The tonic of the scale.
   */
  public Note getTonic() {
    return tonic;
  }

  /**
   * Returns the mode of the scale.
   *
   * @return The mode of the scale.
   */
  public Mode getMode() {
    return mode;
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

}
