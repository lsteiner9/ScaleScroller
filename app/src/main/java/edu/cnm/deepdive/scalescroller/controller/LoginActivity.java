package edu.cnm.deepdive.scalescroller.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.scalescroller.R;
import edu.cnm.deepdive.scalescroller.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.service.PlayerRepository;

/**
 * The launcher activity that allows the user to sign in with Google Sign In.
 */
public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;

  private GoogleSignInService service;
  private ActivityLoginBinding binding;
  private PlayerRepository playerRepository;

  /**
   * Initializes variables and sets an OnClickListener to Google Sign In.
   *
   * @param savedInstanceState A {@code Bundle}.
   */
  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    service = GoogleSignInService.getInstance();
    playerRepository = new PlayerRepository(this);
    //noinspection ResultOfMethodCallIgnored
    service.refresh()
        .subscribe(
            this::updateAndSwitch,
            (throwable) -> {
              binding = ActivityLoginBinding.inflate(getLayoutInflater());
              binding.signIn
                  .setOnClickListener((v) -> service.startSignIn(this, LOGIN_REQUEST_CODE));
              setContentView(binding.getRoot());
            }
        );
  }

  /**
   * Completes signin, if possible.
   *
   * @param requestCode A code that indicates a type of request.
   * @param resultCode  A code that indicates a type of result.
   * @param data        An intent.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service.completeSignIn(data)
          .addOnSuccessListener(this::updateAndSwitch)
          .addOnFailureListener((throwable) ->
              Toast.makeText(this, R.string.login_failure_message, Toast.LENGTH_LONG)
                  .show());

    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  private void updateAndSwitch(GoogleSignInAccount account) {
    //noinspection ResultOfMethodCallIgnored
    playerRepository.createPlayer(account)
        .subscribe(
            (user) -> {
              Intent intent = new Intent(this, MainActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
            },
            (throwable) -> {
              // TODO Remove this after development complete
              Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
              throw new RuntimeException(throwable);
            }
        );

  }
}