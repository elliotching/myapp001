package hexa.app001;


import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewPresenter extends BasePresenter<RecyclerViewMvpView> {
  
  public RecyclerViewPresenter() {
    super();
  }
  
  public NetworkHelper getmNetwork() {
    if(mNetwork == null){
      mNetwork = new NetworkHelper();
    }
    return mNetwork;
  }
  
  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(BaseMvpView) before" +
          " requesting data to the Presenter");
    }
  }
  
  public void loadMovies(String key, String api) {
    // retrieve request from API http://www.omdbapi.com/?i=tt3896198&apikey=dc16346
    checkViewAttached();
    mCompositeDisposable.add(
        mNetwork
            .getRetrofit()
            .create(RetrofitApi.class)
            .getMovies(key, api)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<SearchResponse>() {
              @Override
              public void onNext(SearchResponse search) {
                if(search.getSearchList() != null){
                  getMvpView().populateRecyclerView(search.getSearchList());
                }
                else if(search.getError() != null){
                  getMvpView().showError(search.getError());
                }
              }
  
              @Override
              public void onError(Throwable e) {
    
              }
  
              @Override
              public void onComplete() {
    
              }
            })
    );
  }
  
}
