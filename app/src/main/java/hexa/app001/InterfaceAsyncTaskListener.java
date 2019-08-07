package hexa.app001;

import java.util.ArrayList;

public interface InterfaceAsyncTaskListener {
  void onCompletedInitRecyclerView(ArrayList<Movie> al);
  
  void initData();
  
  // use in Edit Activity (Save)
  void onSaveCompletedInitRecyclerView();
  
  // use in Edit Activity (Select data and fill up all EditText's)
  void onSelectCompleted(Movie copiedMovie);
  
  void onSaveCompletedRefreshViewActivity(Movie copiedMovie);
}
