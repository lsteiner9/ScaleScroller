package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.adapter.HighScoreRecyclerAdapter;
import edu.cnm.deepdive.scalescroller.databinding.FragmentHighScoreBinding;
import edu.cnm.deepdive.scalescroller.model.entity.Player;
import edu.cnm.deepdive.scalescroller.viewmodel.MainViewModel;

// TODO javadoc
public class HighScoreFragment extends Fragment {

  private FragmentHighScoreBinding binding;
  private NavController navController;
  private Player player;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentHighScoreBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    binding.highScoresBackButton.setOnClickListener((v) -> {
      navController.navigate(HighScoreFragmentDirections.openSettings());
    });
    return binding.getRoot();
  }

  // TODO why does getPlayer return null??
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getPlayer().observe(getViewLifecycleOwner(), (p) -> player = p);
    viewModel.getHighScores().observe(getViewLifecycleOwner(), (attempts) -> {
          HighScoreRecyclerAdapter adapter =
              new HighScoreRecyclerAdapter(getContext(), attempts, player);
          binding.scoresRecycler.setAdapter(adapter);
        }
    );
  }
}
