package edu.cnm.deepdive.scalescroller.adapter;

import android.content.ClipData.Item;
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

//TODO javadoc
public class HighScoreRecyclerAdapter extends RecyclerView.Adapter<Holder>{

  private final Context context;
  private final List<ChallengeAttempt> highScoreAttempts;
  private final LayoutInflater inflater;

  public HighScoreRecyclerAdapter(@NonNull Context context, List<ChallengeAttempt> highScoreAttempts) {
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

  class Holder extends RecyclerView.ViewHolder {

    private final ItemScoreBinding binding;

    private Holder(@NonNull ItemScoreBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      if (highScoreAttempts.size() == 0) {
        binding.player.setText(R.string.no_scores);
      }
    }

    private void bind(int position) {
      ChallengeAttempt challengeAttempt = highScoreAttempts.get(position);
      binding.scoreNumber.setText(position + 1);
      // TODO somehow get the player name out of the playerid - how??
//      binding.player.setText(challengeAttempt.getPlayerId());
      binding.timestamp.setText(challengeAttempt.getTimestamp().toString());
    }
  }

}
