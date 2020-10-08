package edu.cnm.deepdive.scalescroller;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class ScaleScrollerApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
