package hexa.app001;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
  // i = tt3896198
  // ("https://www.omdbapi.com/")
  @GET("http://www.omdbapi.com/")
  Observable<SearchResponse> getMovies(
      @Query("s") String s_key,
      @Query("apikey") String api
  );
  
  
  
//  @GET("http://www.omdbapi.com/")
//  Observable<Movie> getMovies(
//      @Query("s") String s_key,
//      @Query("apikey") String api
//  );
  
  @GET("http://www.omdbapi.com/")
  Call<List<Movie>> callMovies(
      @Query("s") String s_key,
      @Query("apikey") String api
  );

}
