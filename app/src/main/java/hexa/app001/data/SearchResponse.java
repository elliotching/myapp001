package hexa.app001.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import hexa.app001.data.Movie;

public class SearchResponse {
  
  @JsonProperty("Search")
  private List<Movie> searchList;
  @JsonProperty("totalResults")
  private String totalResults;
  @JsonProperty("Response")
  private String response;
  @JsonProperty("Error")
  private String error;
  
  public List<Movie> getSearchList() {
    return searchList;
  }
  
  public String getTotalResults() {
    return totalResults;
  }
  
  public String getResponse() {
    return response;
  }
  
  public String getError() {
    return error;
  }
}