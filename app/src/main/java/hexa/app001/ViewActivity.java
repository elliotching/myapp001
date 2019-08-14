package hexa.app001;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewActivity extends AppCompatActivity {
  private final String TAG = this.getClass().getSimpleName();
  private ViewActivity activity = this;
  private Context context = this;
  private ViewPresenter mPresenter;
  private Movie movie;
  @BindView(R2.id.tv_title)
  TextView tvTitle;
  @BindView(R2.id.iv_image)
  ImageView ivImage;
  @BindView(R2.id.tv_subtitle)
  TextView tvSubtitle;
  @BindView(R2.id.tv_description)
  TextView tvDescription;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view);
    ButterKnife.bind(activity);
    Intent i = this.getIntent();
    movie = i.getParcelableExtra(Res.INTENT_EXTRA_KEY_MOVIE);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      if (movie != null) {
        activity.getSupportActionBar().setTitle(movie.getTitle());
      }
    }
    activity.displayMovie();
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      activity.finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // Check which request we're responding to
    if (requestCode == Res.REQUEST_CODE_EDIT_CONTACT_REQUEST) {
      if (resultCode == RESULT_OK) {
        long editingId = data.getLongExtra(Res.INTENT_EXTRA_EDITED_CONTACT_ID, Res.DEFAULT_FALSE);
        if (editingId != Res.DEFAULT_FALSE) {
        
        }
      }
    }
  }
  
  private void displayMovie(){
    tvTitle.setText(movie.getTitle());
    tvSubtitle.setText(movie.getSubtitle());
    tvDescription.setText(movie.getDescription());
    NetworkHelper.setImageUrl(ivImage, movie.getPoster());
  }
}







