package edu.cnm.deepdive.scalescroller.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    indices = {
        @Index(value = {"total_score"}, unique = false),
        @Index(value = {"timestamp"}, unique = true)
    },
    foreignKeys = {
        @ForeignKey(
            entity = Player.class,
            childColumns = "player_id",
            parentColumns = "player_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)

public class ChallengeAttempt {

  @PrimaryKey
  @ColumnInfo(name = "challenge_attempt_id")
  private Long id;

  @ColumnInfo(name = "player_id")
  private Long playerId;

  @ColumnInfo(name = "total_score")
  private int totalScore;

  @NonNull
  private Date timestamp = new Date();

  @ColumnInfo(name = "correct_coins")
  private int correctCoins;

  @ColumnInfo(name = "incorrect_coins")
  private int incorrectCoins;

}
