package edu.cnm.deepdive.scalescroller;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.scalescroller.service.GoogleSignInService;
import edu.cnm.deepdive.scalescroller.service.ScaleScrollerDatabase;
import io.reactivex.schedulers.Schedulers;

/**
 * This class serves as the entry point for the ScaleScroller application.
 */
public class ScaleScrollerApplication extends Application {

  /**
   * Initializes {@link ScaleScrollerDatabase} and {@link GoogleSignInService}.
   */
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    ScaleScrollerDatabase.setContext(this);
    ScaleScrollerDatabase.getInstance().getChallengeAttemptDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    GoogleSignInService.setContext(this);
  }
}
