package hexa.app001.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import hexa.app001.data.Movie;

public class BaseActivity extends AppCompatActivity {
  private BaseActivity mActivity;
  private final String TAG = this.getClass().getSimpleName();
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    Log.d(TAG, "onCreate: BaseActivity");
    super.onCreate(savedInstanceState);
  }
  
  protected Intent startBase(int viewLayout, BaseActivity activity){
    setContentView(viewLayout);
    mActivity = activity;
    ButterKnife.bind(activity);
    return mActivity.getIntent();
  }
  
  protected void setActionBarTitle(@Nullable Movie movie){
    if (mActivity.getSupportActionBar() != null) {
      mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      if (movie != null) {
        mActivity.getSupportActionBar().setTitle(movie.getTitle());
      }
    }
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      mActivity.finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
}
