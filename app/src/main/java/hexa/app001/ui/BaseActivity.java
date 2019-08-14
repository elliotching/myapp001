package hexa.app001.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
  private BaseActivity mActivity;
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  
  protected Intent startBase(int viewLayout, BaseActivity activity){
    setContentView(viewLayout);
    ButterKnife.bind(activity);
    mActivity = activity;
    return mActivity.getIntent();
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
