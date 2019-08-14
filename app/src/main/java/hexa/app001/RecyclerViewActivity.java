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
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewMvpView{
  
  private static final String TAG = "RecyclerViewActivity";
  public final Context context = this;
  public final RecyclerViewActivity activity = this;
  public final RecyclerViewMvpView mvpView = this;
  private RecyclerViewPresenter<RecyclerViewMvpView> mPresenter;
  
  @BindView(R2.id.edt_search)
  EditText edtSearch;
  
  @BindView(R2.id.card_search)
  CardView cardSearch;
  
  @BindView(R2.id.rv_contacts)
  RecyclerView rvContacts;
  
  @BindView(R2.id.tv_error)
  TextView tvErrorSearch;
  
  @BindView(R2.id.pb_loading_search)
  ProgressBar pbLoadingSearch;
//
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    ButterKnife.bind(activity);
    
    // request from http://www.omdbapi.com/?i=tt3896198&apikey=dc16346
    mPresenter = new RecyclerViewPresenter<>();
    mPresenter.attachView(mvpView);
    
    edtSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
  
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(charSequence.length() == 0){
          changeVisibility(0,0,1);
          activity.showHintSearch();
        }
        else {
          activity.showProgressBar();
          mPresenter.search(charSequence.toString());
          mPresenter.loadMovies(charSequence.toString(), Res.API_KEY);
        }
      }
  
      @Override
      public void afterTextChanged(Editable editable) {
    
      }
    });
    
  }
  
  private void changeVisibility(Integer... visibility){
    for(int i = 0; i < visibility.length; i++) {
      switch(i){
        case 0:
          activity.setVisibility(pbLoadingSearch, i);
          break;
        case 1:
          activity.setVisibility(rvContacts, i);
          break;
        case 2:
          activity.setVisibility(tvErrorSearch, i);
          break;
        default:
          break;
      }
    }
  }
  
  private void setVisibility(View v, int i) {
    switch (i) {
      case 0://false
        v.setVisibility(View.GONE);
        break;
      case 1://true
        v.setVisibility(View.VISIBLE);
        break;
      default:
    
    }
  }
  
  private void showHintSearch() {
    tvErrorSearch.setVisibility(View.VISIBLE);
    tvErrorSearch.setText(R.string.hint_msg_search);
  }
  
  
  private void showProgressBar(){
    changeVisibility(1,0,0);
//    pbLoadingSearch.setVisibility(View.VISIBLE);
//    rvContacts.setVisibility(View.GONE);
//    tvErrorSearch.setVisibility(View.GONE);
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
  public void populateRecyclerView(List<Movie> movies) {
    changeVisibility(0,1,0);
//    pbLoadingSearch.setVisibility(View.GONE);
//    rvContacts.setVisibility(View.VISIBLE);
//    tvErrorSearch.setVisibility(View.GONE);
    
    ArrayList<Movie> amovies = new ArrayList<>(movies);
    MovieAdapter movieAdapter = new MovieAdapter(activity, amovies);
    rvContacts.setAdapter(movieAdapter);
    rvContacts.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
  }
  
  @Override
  public void showError(String error) {
    changeVisibility(0,0,1);
//    rvContacts.setVisibility(View.GONE);
//    pbLoadingSearch.setVisibility(View.GONE);
//    tvErrorSearch.setVisibility(View.VISIBLE);
    
    error = Res.get(context, R.string.error_text) + error;
    
    tvErrorSearch.setText(error);
  }
}
