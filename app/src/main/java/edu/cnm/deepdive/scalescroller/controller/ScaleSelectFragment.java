package edu.cnm.deepdive.scalescroller.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.adapter.ScaleRecyclerAdapter;
import edu.cnm.deepdive.scalescroller.databinding.FragmentScaleSelectBinding;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.service.PlayerRepository;
import edu.cnm.deepdive.scalescroller.viewmodel.MainViewModel;

/**
 * Allows the user to select a scale from a RecyclerView for Learn mode.
 */
public class ScaleSelectFragment extends Fragment {

  private FragmentScaleSelectBinding binding;
  private NavController navController;
  private GoogleSignInService signInService;
  private PlayerRepository playerRepository;
  private int highestDifficulty;

  /**
   * Sets up navigation and the RecyclerView adapter.
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
    binding = FragmentScaleSelectBinding.inflate(inflater);
    navController = NavHostFragment.findNavController(this);
    binding.scaleSelectBackButton.setOnClickListener((v) -> {
      navController.navigate(ScaleSelectFragmentDirections.openTitle());
    });
    signInService = GoogleSignInService.getInstance();
    playerRepository = new PlayerRepository(getActivity());
    return binding.getRoot();
  }

  /**
   * Creates a recycler view containing the scales in the database, with scales the user cannot
   * select displayed as grayed-out.
    * @param view A {@code View} object.
   * @param savedInstanceState A {@code Bundle}
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    playerRepository.getByOauth(signInService.getAccount().getId())
        .observe(getViewLifecycleOwner(),
            player -> highestDifficulty = player.getHighestLearnLevel());
    viewModel.getScales().observe(getViewLifecycleOwner(), (scales) -> {
      //noinspection ConstantConditions
      ScaleRecyclerAdapter adapter = new ScaleRecyclerAdapter(getContext(), scales, (scale) -> {
        if (scale.getDifficulty() > highestDifficulty) {
          Toast.makeText(getContext(), R.string.scale_select_error, Toast.LENGTH_LONG)
              .show();
        } else {
          navController.navigate(
              ScaleSelectFragmentDirections.openLearnModeGame(scale.getTonic(), scale.getMode()));
        }
      },
          highestDifficulty);
      binding.scaleRecycler.setAdapter(adapter);
    });
  }
}
