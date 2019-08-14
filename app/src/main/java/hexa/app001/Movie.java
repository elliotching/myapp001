package hexa.app001;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Parcelable {
  
  @JsonProperty("Title")
  private String Title;
  @JsonProperty("Year")
  private String Year;
  @JsonProperty("imdbID")
  private String imdbID;
  @JsonProperty("Type")
  private String Type;
  @JsonProperty("Poster")
  private String Poster;
  
  
  @JsonProperty("Title")
  public String getTitle() {
    return Title;
  }
  
  @JsonProperty("Title")
  public void setTitle(String title) {
    Title = title;
  }
  
  @JsonProperty("Year")
  public String getYear() {
    return Year;
  }
  
  @JsonProperty("Year")
  public void setYear(String year) {
    Year = year;
  }
  
  @JsonProperty("imdbID")
  public String getImdbID() {
    return imdbID;
  }
  
  @JsonProperty("imdbID")
  public void setImdbID(String imdbID) {
    this.imdbID = imdbID;
  }
  
  @JsonProperty("Type")
  public String getType() {
    return Type;
  }
  
  @JsonProperty("Type")
  public void setType(String type) {
    Type = type;
  }
  
  @JsonProperty("Poster")
  public String getPoster() {
    return Poster;
  }
  
  @JsonProperty("Poster")
  public void setPoster(String poster) {
    Poster = poster;
  }
  
  @Override
  public int describeContents() {
    return 0;
  }
  
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.Title);
    dest.writeString(this.Year);
    dest.writeString(this.imdbID);
    dest.writeString(this.Type);
    dest.writeString(this.Poster);
  }
  
  public Movie() {
  }
  
  protected Movie(Parcel in) {
    this.Title = in.readString();
    this.Year = in.readString();
    this.imdbID = in.readString();
    this.Type = in.readString();
    this.Poster = in.readString();
  }
  
  public static final Creator<Movie> CREATOR = new Creator<Movie>() {
    @Override
    public Movie createFromParcel(Parcel source) {
      return new Movie(source);
    }
    
    @Override
    public Movie[] newArray(int size) {
      return new Movie[size];
    }
  };
}