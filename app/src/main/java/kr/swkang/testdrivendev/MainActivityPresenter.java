package kr.swkang.testdrivendev;

import android.support.annotation.NonNull;

import kr.swkang.testdrivendev.utils.SimpleIdlingResource;
import kr.swkang.testdrivendev.utils.mvp.BasePresenter;
import kr.swkang.testdrivendev.utils.mvp.BaseView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author KangSungWoo
 * @since 2016-09-22
 */
public class MainActivityPresenter
    extends BasePresenter {
  private static final String TAG = MainActivityPresenter.class.getSimpleName();

  private MainActivityPresenter.View view;
  private SimpleIdlingResource       idlingResource;

  public MainActivityPresenter(@NonNull MainActivityPresenter.View activity) {
    this.view = activity;
    if (activity instanceof MainActivity) {
      idlingResource = ((MainActivity) activity).getIdlingResource();
    }
  }

  public void getTextDatas() {
    Observable observable = Observable.create(
        new Observable.OnSubscribe<String>() {
          @Override
          public void call(Subscriber<? super String> subscriber) {
            try {
              Thread.sleep(1000); // DUMMY VALUE
            } catch (InterruptedException ie) {
              subscriber.onError(ie);
            } finally {
              subscriber.onNext("KANG");
            }
          }
        }
    ).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    observable.subscribe(
        new Subscriber<String>() {
          @Override
          public void onCompleted() {
          }

          @Override
          public void onError(Throwable e) {
            idlingResource.setIdleState(true);
            view.onError(TAG, e != null ? e.getMessage() : "ERROR");
          }

          @Override
          public void onNext(String result) {
            idlingResource.setIdleState(true);
            view.updateTextView(result);
          }
        }
    );
    idlingResource.setIdleState(false);
  }

  public interface View
      extends BaseView {
    void updateTextView(String message);
  }
}
