package hexa.app001;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T> {
  protected final String TAG = this.getClass().getSimpleName();
  protected T mMvpView;
  protected CompositeDisposable mCompositeDisposable;
  protected NetworkHelper mNetwork;
  
  
  public BasePresenter() {
    mCompositeDisposable = new CompositeDisposable();
    mNetwork = new NetworkHelper();
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
}
