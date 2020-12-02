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
import edu.cnm.deepdive.scalescroller.viewmodel.MainViewModel;

/**
 * Allows the user to view high scores from Challenge mode from a RecyclerView.
 */
public class HighScoreFragment extends Fragment {

  private FragmentHighScoreBinding binding;
  private NavController navController;

  /**
   * Sets up navigation.
   *
   * @param inflater           A {@code LayoutInflater}.
   * @param container          A {@code ViewGroup}.
   * @param savedInstanceState A {@code Bundle}.
   * @return A {@code View}.
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentHighScoreBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    binding.highScoresBackButton.setOnClickListener((v) ->
        navController.navigate(HighScoreFragmentDirections.openSettings()));
    return binding.getRoot();
  }

  /**
   * Creates a recycler view containing the high scores from the database, with information about
   * the player and timestamp for each score.
   *
   * @param view               A {@code View} object.
   * @param savedInstanceState A {@code Bundle}
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getHighScores().observe(getViewLifecycleOwner(), (attempts) -> {
          //noinspection ConstantConditions
          HighScoreRecyclerAdapter adapter =
              new HighScoreRecyclerAdapter(getContext(), attempts);
          binding.scoresRecycler.setAdapter(adapter);
        }
    );
  }
}
