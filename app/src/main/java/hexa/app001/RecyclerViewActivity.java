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

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewMvpView{
  
  public final Context context = this;
  public final AppCompatActivity activity = this;
  public final RecyclerViewActivity thisInterface = this;
  private RecyclerView rvContacts;
  
  private RecyclerViewPresenter<RecyclerViewMvpView> mRecyclerViewPresenter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    
    rvContacts = findViewById(R.id.rvContacts);
    
    // request from http://www.omdbapi.com/?i=tt3896198&apikey=dc16346
    mRecyclerViewPresenter = new RecyclerViewPresenter<>();
    mRecyclerViewPresenter.attachView(this);
    mRecyclerViewPresenter.getMovies();
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == Res.REQUEST_CODE_SELECT_CONTACT_REQUEST) {
      if (resultCode == RESULT_OK) {
      
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
  
  @Override
  public void onRetrievedSuccess() {
  
  }
  
  @Override
  public void populateRecyclerView(ArrayList<Movie> movies) {
    MovieAdapter movieAdapter = new MovieAdapter(activity, movies);
    rvContacts.setAdapter(movieAdapter);
    rvContacts.setLayoutManager(new LinearLayoutManager(context));
  }


//  // Override from Interface
//  @Override
//  public void onCompletedInitRecyclerView(ArrayList<Movie> al) {
//    MovieAdapter adapter = new MovieAdapter(activity, al);
//    rvContacts.setAdapter(adapter);
//    rvContacts.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
//  }
  
}
