package hexa.app001.ui;

import java.util.List;

import hexa.app001.data.Movie;

public interface RecyclerViewMvpView {
  
  void populateRecyclerView(List<Movie> movies);
  
  void showError(String error);
}
