package com.qflair.opentrace;

import android.os.Trace;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;

public class OpenTrace {

  private static volatile boolean sEnabled = false;

  public static boolean isEnabled() {
    return sEnabled;
  }

  public static void enable() {
    sEnabled = true;
    Method appTracingAllowed = getAppTracingAllowedMethod();
    try {
      appTracingAllowed.invoke(null /* obj */, true /* args */);
    } catch (IllegalAccessException | InvocationTargetException | NullPointerException e) {
      throw new IllegalStateException("Can't enable app tracing on this device.");
    }
  }

  @Nullable
  private static Method getAppTracingAllowedMethod() {
    try {
      //noinspection JavaReflectionMemberAccess
      return Trace.class.getMethod("setAppTracingAllowed", boolean.class);
    } catch (NoSuchMethodException e) {
      return null;
    }
  }
}
