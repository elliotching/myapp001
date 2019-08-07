package hexa.app001;


import java.util.ArrayList;

public class RecyclerViewPresenter<T extends RecyclerViewMvpView> {
  
  private T mMvpView;
  
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
    if (!isViewAttached()) throw new MvpViewNotAttachedException();
  }
  
  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(BaseMvpView) before" +
          " requesting data to the Presenter");
    }
  }
  
  public ArrayList<Movie> getMovies() {
    // retrieve request from API http://www.omdbapi.com/?i=tt3896198&apikey=dc16346
    
    return new ArrayList<>();
  }
}
