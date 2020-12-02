package edu.cnm.deepdive.scalescroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.adapter.ScaleRecyclerAdapter.Holder;
import edu.cnm.deepdive.scalescroller.databinding.ItemScaleBinding;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import java.util.List;

//TODO javadoc
/**
 * Allows for the scales in the database to be listed in a RecyclerView.
 */
public class ScaleRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private static final String SCALE_FORMAT = "%s %s";

  private final OnScaleClickListener listener;
  private final Context context;
  private final List<Scale> scales;
  private final LayoutInflater inflater;
  private final int highestDifficulty;

  /**
   * The constructor initializes the context, the list of scales, an onClickListener, and the
   * highest difficulty scale the player has access to.
   *
   * @param context
   */
  public ScaleRecyclerAdapter(@NonNull Context context, List<Scale> scales,
      OnScaleClickListener listener, int highestDifficulty) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.scales = scales;
    this.listener = listener;
    this.highestDifficulty = highestDifficulty;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemScaleBinding binding = ItemScaleBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return scales.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemScaleBinding binding;

    private Holder(@NonNull ItemScaleBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    //TODO why isn't the color working consistently?
    private void bind(int position) {
      Scale scale = scales.get(position);
      binding.scaleName.setText(String.format(SCALE_FORMAT,
          scale.getTonic().toString().toUpperCase(),
          scale.getMode().toString().toLowerCase()));
      if (position > highestDifficulty) {
        binding.scaleName.setTextColor(ContextCompat.getColor(context, R.color.inactiveColor));
      }
      itemView.setOnClickListener((v) -> listener.onClick(scale));
    }
  }

  public interface OnScaleClickListener {

    void onClick(Scale scale);

  }

}
