package edu.cnm.deepdive.scalescroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.scalescroller.adapter.ScaleRecyclerAdapter.Holder;
import edu.cnm.deepdive.scalescroller.databinding.ItemScaleBinding;
import edu.cnm.deepdive.scalescroller.model.entity.Scale;
import edu.cnm.deepdive.scalescroller.service.ScaleRepository;
import java.util.List;

public class ScaleRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private static final String SCALE_FORMAT = "%s %s";

  private final Context context;
  private final List<Scale> scales;
  private final LayoutInflater inflater;
  private final ScaleRepository scaleRepository;

  public ScaleRecyclerAdapter(@NonNull Context context) {
    this.context = context;
    scaleRepository = new ScaleRepository(context);
    scales = scaleRepository.getAll().getValue();
    inflater = LayoutInflater.from(context);
  }

  public List<Scale> getScales() {
    return scales;
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

    private void bind(int position) {
      Scale scale = scales.get(position);
      binding.scaleName.setText(String.format(SCALE_FORMAT, scale.getTonic().toString().toUpperCase(), scale.getMode().toString().toLowerCase()));
    }


  }
}
