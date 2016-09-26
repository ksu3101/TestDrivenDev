package kr.swkang.testdrivendev.utils;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author KangSungWoo
 * @since 2016-09-23
 */
public class SimpleIdlingResource
    implements IdlingResource {
  private volatile ResourceCallback resourceCallback;
  private AtomicBoolean isIdleNow = new AtomicBoolean();

  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public boolean isIdleNow() {
    return isIdleNow.get();
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback callback) {
    this.resourceCallback = callback;
  }

  public void setIdleState(boolean isIdleNow) {
    this.isIdleNow.set(isIdleNow);
    if (isIdleNow && resourceCallback != null) {
      resourceCallback.onTransitionToIdle();
    }
  }

}
