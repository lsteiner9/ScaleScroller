package edu.cnm.deepdive.scalescroller.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.scalescroller.databinding.FragmentScaleSelectBinding;

public class ScaleSelectFragment extends Fragment {

  FragmentScaleSelectBinding binding;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentScaleSelectBinding.inflate(inflater);
    binding.scaleSelectBackButton.setOnClickListener((v) -> {
      Intent intent = new Intent(getActivity(), TitleActivity.class)
          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
    // TODO Set listener for the elements in the recycler view
    return binding.getRoot();
  }
}
