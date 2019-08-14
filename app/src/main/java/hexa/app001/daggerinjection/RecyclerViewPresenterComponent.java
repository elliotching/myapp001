package hexa.app001.daggerinjection;

import dagger.Component;
import hexa.app001.ui.RecyclerViewActivity;
import hexa.app001.ui.RecyclerViewPresenter;

@Component
public interface RecyclerViewPresenterComponent {

//  RecyclerViewPresenter getPresenter();
  
  void inject(RecyclerViewActivity rvActivity);
}
