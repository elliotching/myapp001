package hexa.app001;

import java.util.ArrayList;

public interface RecyclerViewMvpView {
  
  void onRetrievedSuccess();
  
  
  void populateRecyclerView(ArrayList<Movie> movies);
}
