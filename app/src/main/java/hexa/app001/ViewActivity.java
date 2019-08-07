package hexa.app001;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class ViewActivity extends AppCompatActivity {
  private AppCompatActivity activity = this;
  private Context context = this;
  private TextView tvTitle;
  private ImageView ivImage;
  private TextView tvSubtitle;
  private TextView tvDescription;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    Intent intent = activity.getIntent();
    final long contactId = intent.getLongExtra(Res.INTENT_EXTRA_KEY_CLICKED_ID, -1);
//    AsyncSelectContact async = new AsyncSelectContact();
//    async.execute(contactId);
    
//    FloatingActionButton editFab = findViewById(R.id.fab_edit);
    tvTitle = findViewById(R.id.tv_title);
    ivImage = findViewById(R.id.iv_image);
    tvSubtitle = findViewById(R.id.tv_subtitle);
    tvDescription = findViewById(R.id.tv_description);
    
    
//    editFab.setOnClickListener(view -> {
//      // to EDIT!
//      Intent intent1 = new Intent(context, EditActivity.class);
//      intent1.putExtra(Res.INTENT_EXTRA_EDIT_CONTACT_ID, contactId);
//      intent1.putExtra(Res.INTENT_EXTRA_STATUS_CODE, 1);
//      startActivityForResult(intent1, Res.REQUEST_CODE_EDIT_CONTACT_REQUEST);
//    });
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      
      Intent intent = new Intent();
      intent.putExtra(Res.INTENT_EXTRA_STATUS_CODE, 1);
      activity.setResult(AppCompatActivity.RESULT_OK, intent);
      activity.finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Check which request we're responding to
    if (requestCode == Res.REQUEST_CODE_EDIT_CONTACT_REQUEST) {
      if (resultCode == RESULT_OK) {
        long editingId = data.getLongExtra(Res.INTENT_EXTRA_EDITED_CONTACT_ID, Res.DEFAULT_FALSE);
        if (editingId != Res.DEFAULT_FALSE) {
//          AsyncUpdateContact async = new AsyncUpdateContact();
//          async.execute(editingId);
        }
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
  
//
//  private class AsyncUpdateContact extends AsyncTask<Long, Void, Void> {
//
//    // execute() >>> init asynctask
//    // AsyncTask<Param, Progress, Result>
//    // doInBackground, end return must be going >>> Post (RESULT Type)
//    // doInBackground, mid publishProg must be going >>> ProgUpd (PROGRESS Type)
//
//    private Realm realm;
    private Movie copiedMovie;
    
    
//    @Override
//    protected Void doInBackground(Long[] params) {
//      this.realm = Realm.getInstance(Res.realmConfig());
//      realm.executeTransaction(realm1 -> {
//        Movie readContact = realm1.where(Movie.class).equalTo("id", params[0]).findFirst();
//        if (readContact != null) {
//          copiedMovie = realm1.copyFromRealm(readContact);
//        }
//      });
//      return null;
//    }
    
//    @Override
//    protected void onPostExecute(Void result) {
//      super.onPostExecute(result);
//
//      if (copiedMovie.getTitle() != null) {
//        tvTitle.setText(copiedMovie.getTitle());
//        tvSubtitle.setText(copiedMovie.getSubtitle());
//        tvDescription.setText(copiedMovie.getDesc());
//        Picasso.get()
//            .load(copiedMovie.getImage())
//            .resize(1280, 0)
//            .placeholder(R.drawable.ic_pending)
//            .error(R.drawable.ic_broken)
//            .into(ivImage);
//      }
//    }
//  }
  
//
//  private class AsyncSelectContact extends AsyncTask<Long, Void, Void> {
//    private Realm realm;
//    private Movie copiedMovie;
//
//    @Override
//    protected Void doInBackground(Long[] params) {
//      this.realm = Realm.getInstance(Res.realmConfig());
//
//      realm.executeTransaction(realm1 -> {
//        Realm realm = Realm.getInstance(Res.realmConfig());
//        Movie movie = realm.where(Movie.class).equalTo("id", params[0]).findFirst();
//        copiedMovie = realm1.copyFromRealm(movie);
//      });
//      return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void result) {
//      super.onPostExecute(result);
//
//      if (copiedMovie != null) {
//        activity.getSupportActionBar().setTitle(copiedMovie.getTitle());
//        tvTitle.setText(copiedMovie.getTitle());
//        Picasso.get()
//            .load(copiedMovie.getImage())
//            .resize(1280, 0)
//            .placeholder(R.drawable.ic_pending)
//            .error(R.drawable.ic_broken)
//            .into(ivImage);
//        tvSubtitle.setText(copiedMovie.getSubtitle());
//        tvDescription.setText(copiedMovie.getDesc());
//      }
//    }
//  }
}







