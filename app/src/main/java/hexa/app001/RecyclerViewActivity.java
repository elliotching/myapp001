package hexa.app001;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewMvpView{
  
  private static final String TAG = "RecyclerViewActivity";
  public final Context context = this;
  public final AppCompatActivity activity = this;
  public final RecyclerViewActivity thisInterface = this;
  private RecyclerView rvContacts;
  
  @BindView(R2.id.edt_search)
  EditText edtSearch;
  
  @BindView(R2.id.card_search)
  CardView cardSearch;
  
  private RecyclerViewPresenter<RecyclerViewMvpView> mRecyclerViewPresenter;
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    ButterKnife.bind(activity);
    rvContacts = findViewById(R.id.rvContacts);
    
    // request from http://www.omdbapi.com/?i=tt3896198&apikey=dc16346
    mRecyclerViewPresenter = new RecyclerViewPresenter<>();
    mRecyclerViewPresenter.attachView(this);
    mRecyclerViewPresenter.getMovies();
    
    edtSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
  
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String currentText = edtSearch.getText().toString();
        Log.d(TAG, "onTextChanged: "+currentText);
      }
  
      @Override
      public void afterTextChanged(Editable editable) {
    
      }
    });
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
    rvContacts.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
  }
  
}
