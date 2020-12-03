package edu.cnm.deepdive.scalescroller.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Player;

/**
 * {@code ChallengeAttemptWithPlayer} extends the {@link ChallengeAttempt} entity to provide support
 * for queries that need the {@link Player} object in addition to the ChallengeAttempt object.
 */
public class ChallengeAttemptWithPlayer extends ChallengeAttempt {

  @Relation(entity = Player.class, entityColumn = "player_id", parentColumn = "player_id")
  private Player player;

  /**
   * Returns the {@code Player} associated with the {@code ChallengeAttempt}.
   *
   * @return The player associated with the attempt.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets the {@code Player} associated with the {@code ChallengeAttempt}.
   *
   * @param player The {@code Player} object to be set.
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

}
