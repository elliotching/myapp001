package hexa.app001.ui;


import hexa.app001.data.RetrofitApi;
import hexa.app001.data.SearchResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewPresenter extends BasePresenter<RecyclerViewMvpView> {
  
  public RecyclerViewPresenter() {
    super();
  }
  
  public void loadMovies(String key, String api) {
    // retrieve request from API http://www.omdbapi.com/?i=tt3896198&apikey=dc16346
    checkViewAttached();
    getCompositeDisposable().add(
        getNetwork()
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
