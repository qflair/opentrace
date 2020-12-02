package com.example.opentrace;

import android.app.Application;

import androidx.core.os.TraceCompat;

public class SampleApplication extends Application {

  @Override
  public void onCreate() {
    TraceInit.init();
    TraceCompat.beginSection("SampleApplication.onCreate");
    super.onCreate();
    // other initialization, like Crashlytics
    TraceCompat.endSection();
  }
}
