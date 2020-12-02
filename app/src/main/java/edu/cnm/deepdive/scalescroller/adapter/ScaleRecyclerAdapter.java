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
   * highest difficulty scale the current player has access to.
   *
   * @param context           The application context.
   * @param scales            A {@code List} of {@link Scale} to be displayed.
   * @param listener          A type of onClickListener.
   * @param highestDifficulty The highest difficulty scale the current player has access to.
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

  /**
   * A ViewHolder class that aids in setting and inflating xml in the RecyclerView.
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemScaleBinding binding;

    /**
     * The constructor initializes the viewBinding.
     *
     * @param binding A {@code ItemScaleBinding} object.
     */
    private Holder(@NonNull ItemScaleBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    /**
     * Binds each {@code Scale} to its corresponding position in the RecyclerView, and sets the text
     * for the tonic and mode of the scale. Grays out the scales that are not accessible to the user.
     *
     * @param position The scale's position in the RecyclerView and the scales list.
     */
    private void bind(int position) {
      Scale scale = scales.get(position);
      binding.scaleName.setText(String.format(SCALE_FORMAT,
          scale.getTonic().toString().toUpperCase(),
          scale.getMode().toString().toLowerCase()));
      if (position > highestDifficulty) {
        binding.scaleName.setTextColor(ContextCompat.getColor(context, R.color.inactiveColor));
      } else {
        binding.scaleName.setTextColor(ContextCompat.getColor(context, R.color.textColor));
      }
      itemView.setOnClickListener((v) -> listener.onClick(scale));
    }
  }

  /**
   * This interface can be implemented to handle clicks of scales in the adapter.
   */
  public interface OnScaleClickListener {

    void onClick(Scale scale);

  }

}
