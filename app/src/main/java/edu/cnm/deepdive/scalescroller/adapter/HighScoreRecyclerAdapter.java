package edu.cnm.deepdive.scalescroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.adapter.HighScoreRecyclerAdapter.Holder;
import edu.cnm.deepdive.scalescroller.databinding.ItemScoreBinding;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import java.util.List;

/**
 * Allows for the highest scores from the database to be listed in a RecyclerView.
 */
public class HighScoreRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<ChallengeAttempt> highScoreAttempts;
  private final LayoutInflater inflater;

  /**
   * The constructor initializes the context and the list of high-scoring attempts.
   *
   * @param context           The application context.
   * @param highScoreAttempts The list of high-scoring attempts.
   */
  public HighScoreRecyclerAdapter(@NonNull Context context,
      List<ChallengeAttempt> highScoreAttempts) {
    this.context = context;
    this.highScoreAttempts = highScoreAttempts;
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemScoreBinding binding = ItemScoreBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return highScoreAttempts.size();
  }

  /**
   * A ViewHolder class that aids in setting and inflating xml in the RecyclerView.
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemScoreBinding binding;

    /**
     * The constructor initializes the viewBinding, and also sets the text in case there are no
     * elements to display in the recycler.
     *
     * @param binding A {@code ItemScaleBinding} object.
     */
    private Holder(@NonNull ItemScoreBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      if (highScoreAttempts.size() == 0) {
        binding.player.setText(R.string.no_scores);
      }
    }

    /**
     * Binds each {@link ChallengeAttempt} to its corresponding position in the RecyclerView, and
     * sets the text for the high-score position, the player for that attempt, the high score, and
     * the timestamp.
     *
     * @param position The attempt's position in the RecyclerView and the high-score list.
     */
    private void bind(int position) {
      ChallengeAttempt challengeAttempt = highScoreAttempts.get(position);
      binding.scoreNumber.setText(position + 1);
      // TODO somehow get the player name out of the playerid - how??
//      binding.player.setText(challengeAttempt.getPlayerId());
      //TODO format the total score and the timestamp
      binding.highScore.setText(challengeAttempt.getTotalScore());
      binding.timestamp.setText(challengeAttempt.getTimestamp().toString());
    }
  }

}
