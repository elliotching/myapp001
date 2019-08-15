package hexa.app001.ui.base;

import javax.inject.Inject;

import hexa.app001.data.NetworkHelper;
import hexa.app001.ui.RecyclerViewPresenter;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T> {
  private T mMvpView;
  private CompositeDisposable mCompositeDisposable;
  @Inject
  NetworkHelper mNetwork;
  
  public BasePresenter() {
    mCompositeDisposable = new CompositeDisposable();
  }
  
  public NetworkHelper getNetwork() {
    return mNetwork;
  }
  
  public CompositeDisposable getCompositeDisposable(){
    if(mCompositeDisposable == null){
      mCompositeDisposable = new CompositeDisposable();
    }
    return mCompositeDisposable;
  }
  
  public void attachView(T mvpView) {
    mMvpView = mvpView;
  }
  
  public void detachView() {
    mMvpView = null;
  }
  
  
  public boolean isViewAttached() {
    return mMvpView != null;
  }
  
  public T getMvpView() {
    return mMvpView;
  }
  
  public void checkViewAttached() {
    if (!isViewAttached()) throw new RecyclerViewPresenter.MvpViewNotAttachedException();
  }
  
  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(BaseMvpView) before" +
          " requesting data to the Presenter");
    }
  }
}
