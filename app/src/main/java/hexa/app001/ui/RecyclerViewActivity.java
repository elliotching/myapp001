package hexa.app001.ui;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import hexa.app001.daggerinjection.DaggerRecyclerViewPresenterComponent;
import hexa.app001.daggerinjection.RecyclerViewPresenterComponent;
import hexa.app001.data.Movie;
import hexa.app001.adapter.MovieAdapter;
import hexa.app001.R;
import hexa.app001.R2;
import hexa.app001.data.Res;
import hexa.app001.ui.base.BaseActivity;

public class RecyclerViewActivity extends BaseActivity implements RecyclerViewMvpView{
  
  private static final String TAG = "RecyclerViewActivity";
  public final Context context = this;
  public final RecyclerViewActivity mActivity = this;
  public final RecyclerViewMvpView mvpView = this;
  
  @Inject
  RecyclerViewPresenter mPresenter;
  
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
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    startBase(R.layout.activity_recycler_view, mActivity);
    
    // request from http://www.omdbapi.com/?i=tt3896198&apikey=dc16346
    RecyclerViewPresenterComponent component = DaggerRecyclerViewPresenterComponent.create();
    component.inject(this);
    
    mPresenter.attachView(mvpView);
    
    mActivity.showHintSearch();
    
    edtSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }
  
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(charSequence.length() == 0){
          mActivity.showHintSearch();
        }
        else {
          mActivity.showProgressBar();
          mPresenter.loadMovies(charSequence.toString(), Res.API_KEY);
        }
      }
  
      @Override
      public void afterTextChanged(Editable editable) {
    
      }
    });
    
  }
  
  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresenter.detachView();
  }
  
  // 0 : pbLoadingSearch (Progress Indicator)
  // 1 : rvContacts      (RecyclerView Contacts)
  // 2 : tvErrorSearch   (TextView for Errors)
  private void changeVisibility(Integer... visibility){
    // 0 : pbLoadingSearch
    mActivity.setVisibility(pbLoadingSearch, visibility[0]);
    
    // 1 : rvContacts
    mActivity.setVisibility(rvContacts, visibility[1]);
    
    // 2 : tvErrorSearch
    mActivity.setVisibility(tvErrorSearch, visibility[2]);
  }
  
  private void setVisibility(View v, int i) {
    switch (i) {
      case 0://false
        v.setVisibility(View.GONE);
        break;
      case 1://true
        v.setVisibility(View.VISIBLE);
        break;
    }
  }
  
  private void showHintSearch() {
    changeVisibility(0,0,1); // pb:0 , rv:0 , tv:1
    tvErrorSearch.setText(R.string.hint_msg_search);
  }
  
  private void showProgressBar(){
    changeVisibility(1,0,0); // pb:1 , rv:0 , tv:0
  }
  
  @Override
  public void populateRecyclerView(List<Movie> movies) {
    changeVisibility(0,1,0);  // pb:0 , rv:1 , tv:0
    
    ArrayList<Movie> amovies = new ArrayList<>(movies);
    MovieAdapter movieAdapter = new MovieAdapter(amovies, new MovieAdapter.AdapterCallback() {
      @Override
      public void onClickView(View clickedView) {
        int position = rvContacts.getChildLayoutPosition(clickedView); // gets item position
        if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
          Movie movie = amovies.get(position);
          Intent intent = new Intent(context, ViewActivity.class);
          intent.putExtra(Res.INTENT_EXTRA_KEY_MOVIE, movie);
          mActivity.startActivityForResult(intent, Res.REQUEST_CODE_SELECT_CONTACT_REQUEST);
        }
      }
    });
    rvContacts.setAdapter(movieAdapter);
    rvContacts.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
  }
  
  @Override
  public void showError(String error) {
    changeVisibility(0,0,1); // pb:0 , rv:0 , tv:1
    error = Res.get(context, R.string.error_text) + error;
    tvErrorSearch.setText(error);
  }
}
