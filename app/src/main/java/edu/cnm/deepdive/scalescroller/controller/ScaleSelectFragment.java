package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.adapter.ScaleRecyclerAdapter;
import edu.cnm.deepdive.scalescroller.databinding.FragmentScaleSelectBinding;

public class ScaleSelectFragment extends Fragment {

  private FragmentScaleSelectBinding binding;
  private ScaleRecyclerAdapter adapter;
  private NavController navController;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentScaleSelectBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    binding.scaleSelectBackButton.setOnClickListener((v) -> {
      navController.navigate(ScaleSelectFragmentDirections.openTitle());
    });
    // TODO Set listener for the elements in the recycler view
    binding.scaleRecycler.setAdapter(adapter);
    return binding.getRoot();
  }



}
