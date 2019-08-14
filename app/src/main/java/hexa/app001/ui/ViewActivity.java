package hexa.app001.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import hexa.app001.data.Movie;
import hexa.app001.data.NetworkHelper;
import hexa.app001.R;
import hexa.app001.R2;
import hexa.app001.data.Res;

public class ViewActivity extends BaseActivity {
  private final String TAG = this.getClass().getSimpleName();
  private ViewActivity activity = this;
  private Context context = this;
  
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
    Intent i = startBase(R.layout.activity_recycler_view, activity);
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







