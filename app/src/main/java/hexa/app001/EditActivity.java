//package hexa.app001;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.EditText;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.databinding.DataBindingUtil;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import hexa.app001.databinding.ActivityEditBinding;
//import io.realm.Realm;
//import io.realm.RealmResults;
//
//public class EditActivity extends AppCompatActivity implements InterfaceAsyncTaskListener {
//  private AppCompatActivity activity = this;
//  private Context context = this;
//  private static int status = 0; // 0 = ADD NEW , 1 = EDIT
//  private ActivityEditBinding binding;
//  private long contactId;
//
//  @BindView(R.id.edit_title)
//  EditText editTitle;
//
//  @BindView(R.id.edit_subtitle)
//  EditText editSubtitle;
//
//  @Override
//  protected void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    binding = DataBindingUtil.setContentView(activity, R.layout.activity_edit);
//
//    this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    ButterKnife.bind(this);
//    Intent intent = activity.getIntent();
//    contactId = intent.getLongExtra(Res.INTENT_EXTRA_EDIT_CONTACT_ID, -1);
//    status = intent.getIntExtra(Res.INTENT_EXTRA_STATUS_CODE, -1);
//
//    AsyncSelectContact async = new AsyncSelectContact(this);
//    async.execute(contactId);
//  }
//
//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    activity.getMenuInflater().inflate(R.menu.menus, menu);
//    return true;
//  }
//
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    if (item.getItemId() == android.R.id.home) {
//      activity.finish();
//      return true;
//    }
//
//    //noinspection SimplifiableIfStatement
//    if (item.getItemId() == R.id.action_save) {
//      AsyncSaveContact async = new AsyncSaveContact(this);
//      async.execute(contactId);
//
//      Intent intent = new Intent();
//      intent.putExtra(Res.INTENT_EXTRA_EDITED_CONTACT_ID, contactId);
//      activity.setResult(AppCompatActivity.RESULT_OK, intent);
//      activity.finish();
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }
//
//  @Override
//  public void onCompletedInitRecyclerView(ArrayList<Movie> al) {
//
//  }
//
//  @Override
//  public void initData() {
//
//  }
//
//  @Override
//  public void onSaveCompletedInitRecyclerView() {
//
//  }
//
//  @Override
//  public void onSelectCompleted(Movie copiedMovie) {
//    // From VIEW or from LIST
//    // click on VIEW -> EDIT
//    // click on LIST -> ADD NEW
//
//    if (copiedMovie == null) {
//      status = 0;
//      activity.getSupportActionBar().setTitle(R.string.new_item);
//    } else {
//      status = 1;
//      activity.getSupportActionBar().setTitle(Res.get(context, R.string.title_editing) + copiedMovie.getTitle());
//      binding.setContact(copiedMovie);
//      Picasso.get()
//          .load(copiedMovie.getImage())
//          .resize(1280, 0)
//          .placeholder(R.drawable.ic_pending)
//          .error(R.drawable.ic_broken)
//          .into(binding.imagePreview);
//    }
//
//    binding.editSubtitle.addTextChangedListener(new TextWatcher() {
//      public void afterTextChanged(Editable s) {
//      }
//
//      public void beforeTextChanged(CharSequence q, int s, int c, int a) {
//      }
//
//      public void onTextChanged(CharSequence q, int s, int b, int c) {
//        int L = binding.editSubtitle.getLineCount();
//        if (L > 3) {
//          binding.editSubtitle.getText().delete(binding.editSubtitle.getSelectionEnd() - 1, binding.editSubtitle.getSelectionStart());
//        }
//      }
//    });
//    binding.etImage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//      @Override
//      public void onFocusChange(View view, boolean b) {
//        if (!b) {
//          if (binding.etImage.getText().toString().isEmpty()) {
//            binding.etImage.setText(Res.DUMP_LINK);
//          }
//          Picasso.get()
//              .load(binding.etImage.getText().toString())
//              .resize(1280, 0)
//              .placeholder(R.drawable.ic_pending)
//              .error(R.drawable.ic_broken)
//              .into(binding.imagePreview);
//        }
//      }
//    });
//  }
//
//  @Override
//  public void onSaveCompletedRefreshViewActivity(Movie copiedMovie) {
//
//  }
//
//  private class AsyncSelectContact extends AsyncTask<Long, Void, Void> {
//    private Realm realm;
//    private Movie copiedMovie;
//    private InterfaceAsyncTaskListener listener;
//
//    AsyncSelectContact(InterfaceAsyncTaskListener listener) {
//      this.listener = listener;
//    }
//
//    @Override
//    protected Void doInBackground(Long[] params) {
//      this.realm = Realm.getInstance(Res.realmConfig());
//
//      realm.executeTransaction(realm1 -> {
//        Movie movie = realm1.where(Movie.class).equalTo("id", params[0]).findFirst();
//        if (movie != null) {
//          copiedMovie = realm1.copyFromRealm(movie);
//        }
//      });
//      return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void result) {
//      super.onPostExecute(result);
//      listener.onSelectCompleted(copiedMovie);
//    }
//  }
//
//
//  private class AsyncSaveContact extends AsyncTask<Long, Void, Void> {
//    private Realm realm;
//    private long editingId;
//    private Movie copiedMovie;
//    private InterfaceAsyncTaskListener listener;
//
//    AsyncSaveContact(InterfaceAsyncTaskListener listener) {
//      this.listener = listener;
//    }
//
//    @Override
//    protected Void doInBackground(Long[] params) {
//      this.realm = Realm.getInstance(Res.realmConfig());
//      editingId = params[0];
//      realm.executeTransaction(realm1 -> {
//        if (status == 1) {
//          Movie movie = realm1.where(Movie.class).equalTo("id", params[0]).findFirst();
//          movie.setTitle(binding.editTitle.getText().toString());
//          movie.setSubtitle(binding.editSubtitle.getText().toString());
//          movie.setImage(binding.etImage.getText().toString());
//          movie.setDesc(binding.editDescription.getText().toString());
//          copiedMovie = realm1.copyFromRealm(movie);
//        }
//
//        if (status == 0) {
//          RealmResults<Movie> movies = realm1.where(Movie.class).findAll();
//          int id = movies.size() + 1;
//          Movie movie = realm1.createObject(Movie.class, id);
//          movie.setTitle(binding.editTitle.getText().toString());
//          movie.setSubtitle(binding.editSubtitle.getText().toString());
//          movie.setImage(binding.etImage.getText().toString().isEmpty() ? Res.DUMP_LINK : binding.etImage.getText().toString());
//          movie.setDesc(binding.editDescription.getText().toString());
//        }
//      });
//      return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//      super.onPostExecute(aVoid);
//      listener.onSaveCompletedRefreshViewActivity(copiedMovie);
//    }
//  }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
