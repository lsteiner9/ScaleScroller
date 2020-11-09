package edu.cnm.deepdive.scalescroller;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.scalescroller.service.ScaleScrollerDatabase;
import io.reactivex.schedulers.Schedulers;

public class ScaleScrollerApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    ScaleScrollerDatabase.setContext(this);
    ScaleScrollerDatabase.getInstance().getChallengeAttemptDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }
}
