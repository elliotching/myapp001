package hexa.app001;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements InterfaceAsyncTaskListener {
  
  public final Context context = this;
  public final AppCompatActivity activity = this;
  public final RecyclerViewActivity thisInterface = this;
  private RecyclerView rvContacts;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    
    rvContacts = findViewById(R.id.rvContacts);
    FloatingActionButton fab = findViewById(R.id.fab_add);
    
    AsyncRealmTask async = new AsyncRealmTask(context, thisInterface);
    async.execute(Res.ASYNC_CODE_SELECT_ALL);
    
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // to Add!
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(Res.INTENT_EXTRA_STATUS_CODE, 0);
        startActivityForResult(intent, Res.REQUEST_CODE_EDIT_CONTACT_REQUEST);
      }
    });
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == Res.REQUEST_CODE_SELECT_CONTACT_REQUEST) {
      if (resultCode == RESULT_OK) {
        
        AsyncRealmTask async = new AsyncRealmTask(context, thisInterface);
        async.execute(Res.ASYNC_CODE_SELECT_ALL);
        Log.d("s", "ssss");
      }
    } else if (requestCode == Res.REQUEST_CODE_EDIT_CONTACT_REQUEST) {
      if (resultCode == RESULT_OK) {
        
        AsyncRealmTask async = new AsyncRealmTask(context, thisInterface);
        async.execute(Res.ASYNC_CODE_SELECT_ALL);
        Log.d("s", "ssss");
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
  
  private void clearAll() {
    AsyncRealmTask async = new AsyncRealmTask(context, thisInterface);
    async.execute(Res.ASYNC_CODE_CLEAR_ALL);
  }
  
  // Override from interface
  @Override
  public void initData() {
    AsyncRealmTask async = new AsyncRealmTask(context, thisInterface);
    async.execute(Res.ASYNC_CODE_INIT_DATA);
  }
  
  @Override
  public void onSaveCompletedInitRecyclerView() {
  
  }
  
  @Override
  public void onSelectCompleted(Movie copiedMovie) {
  
  }
  
  @Override
  public void onSaveCompletedRefreshViewActivity(Movie copiedMovie) {
  
  }
  
  // Override from Interface
  @Override
  public void onCompletedInitRecyclerView(ArrayList<Movie> al) {
    MovieAdapter adapter = new MovieAdapter(activity, al);
    rvContacts.setAdapter(adapter);
    rvContacts.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
  }
  
}
