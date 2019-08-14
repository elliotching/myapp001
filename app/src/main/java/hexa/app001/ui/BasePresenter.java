package hexa.app001.ui;

import hexa.app001.data.NetworkHelper;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T> {
  private T mMvpView;
  private CompositeDisposable mCompositeDisposable;
  private NetworkHelper mNetwork;
  
  public BasePresenter() {
    mCompositeDisposable = new CompositeDisposable();
    mNetwork = new NetworkHelper();
  }
  
  public NetworkHelper getNetwork() {
    if(mNetwork == null){
      mNetwork = new NetworkHelper();
    }
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
