package kr.swkang.testdrivendev;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import kr.swkang.testdrivendev.utils.SimpleIdlingResource;
import kr.swkang.testdrivendev.utils.mvp.BasePresenter;

public class MainActivity
    extends AppCompatActivity
    implements MainActivityPresenter.View {
  @Nullable
  private SimpleIdlingResource  idlingResource;
  private MainActivityPresenter presenter;
  private TextView              textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    presenter = new MainActivityPresenter(this);

    textView = (TextView) findViewById(R.id.main_textview);
    presenter.getTextDatas();

  }

  @VisibleForTesting
  @NonNull
  public SimpleIdlingResource getIdlingResource() {
    if (idlingResource == null) {
      idlingResource = new SimpleIdlingResource();
    }
    return idlingResource;
  }

  @Override
  public void updateTextView(String message) {
    if (textView != null) {
      textView.setText(message != null ? message : "Message is Null..");
    }
  }

  @Override
  public void onError(String tag, String message) {
    updateTextView(tag != null ? tag + (message != null ? ("/ " + message) : "/ message is Null") : "Tag is Null");
  }


}
