package hexa.app001.daggerinjection;

import dagger.Component;
import hexa.app001.ui.BasePresenter;
import hexa.app001.ui.RecyclerViewMvpView;
@Component
public interface NetworkHelperComponent {
  void inject(BasePresenter<RecyclerViewMvpView> presenter);
}
