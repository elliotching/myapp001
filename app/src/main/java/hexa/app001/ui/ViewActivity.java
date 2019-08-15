package hexa.app001.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import hexa.app001.data.Movie;
import hexa.app001.data.NetworkHelper;
import hexa.app001.R;
import hexa.app001.R2;
import hexa.app001.data.Res;
import hexa.app001.ui.base.BaseActivity;

public class ViewActivity extends BaseActivity {
  private final String TAG = this.getClass().getSimpleName();
  private ViewActivity viewActivity = this;
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
    Intent i = startBase(R.layout.activity_view, viewActivity);
    movie = i.getParcelableExtra(Res.INTENT_EXTRA_KEY_MOVIE);
    viewActivity.setActionBarTitle(movie);
    viewActivity.displayMovie();
  }
  
  private void displayMovie(){
    tvTitle.setText(movie.getTitle());
    tvSubtitle.setText(movie.getSubtitle());
    tvDescription.setText(movie.getDescription());
    NetworkHelper.setImageUrl(ivImage, movie.getPoster(), Res.IMAGE_WIDTH_FULL);
  }
}







