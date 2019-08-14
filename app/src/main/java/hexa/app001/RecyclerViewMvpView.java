package hexa.app001;

import java.util.ArrayList;
import java.util.List;

public interface RecyclerViewMvpView {
  
  void onRetrievedSuccess();
  
  
  void populateRecyclerView(List<Movie> movies);
  
  void showError(String error);
}
