package edu.cnm.deepdive.scalescroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.scalescroller.adapter.HighScoreRecyclerAdapter.Holder;
import edu.cnm.deepdive.scalescroller.databinding.ItemScoreBinding;
import edu.cnm.deepdive.scalescroller.model.entity.ChallengeAttempt;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import java.util.List;

/**
 * Designed to only return high scores for the current user.
 */
//TODO javadoc
public class HighScoreRecyclerAdapter extends RecyclerView.Adapter<Holder>{

  private final Context context;
  private final List<ChallengeAttempt> highScoreAttempts;
  private final LayoutInflater inflater;
  private final Player player;

  public HighScoreRecyclerAdapter(@NonNull Context context, List<ChallengeAttempt> highScoreAttempts,
      Player player) {
    this.context = context;
    this.highScoreAttempts = highScoreAttempts;
    inflater = LayoutInflater.from(context);
    this.player = player;
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

  class Holder extends RecyclerView.ViewHolder {

    private final ItemScoreBinding binding;

    private Holder(@NonNull ItemScoreBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      ChallengeAttempt challengeAttempt = highScoreAttempts.get(position);
      binding.scoreNumber.setText(position + 1);
      binding.player.setText(player.getUsername());
      binding.timestamp.setText(challengeAttempt.getTimestamp().toString());
    }
  }

}
