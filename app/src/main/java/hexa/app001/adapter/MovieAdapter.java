package hexa.app001.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hexa.app001.data.Movie;
import hexa.app001.data.NetworkHelper;
import hexa.app001.R;
import hexa.app001.data.Res;
import hexa.app001.ui.ViewActivity;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
  
  private ArrayList<Movie> mMovies;
  private Context context;
  private AdapterCallback adapterCallback;
  
  // Pass in the contact array into the constructor
  public MovieAdapter(ArrayList<Movie> movies, AdapterCallback callback) {
    mMovies = movies;
    this.adapterCallback = callback ;
  }
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    context = parent.getContext();
    
    LayoutInflater inflater = LayoutInflater.from(context);
    
    // Inflate the custom layout
    View movieView = inflater.inflate(R.layout.item_card_view, parent, false);
    
    // Return a new holder instance
    ViewHolder viewHolder = new ViewHolder(movieView);
    
    movieView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        adapterCallback.onClickView(view);
      }
    });
    
    return viewHolder;
  }
  
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // Get the data model based on position
    Movie movie = mMovies.get(position);
    
    TextView tvTitle = holder.tvTitle;
    ImageView img = holder.imgPoster;
    TextView tvSubtitle = holder.tvSubtitle;
    TextView tvDescription = holder.tvDescription;
    
    NetworkHelper.setImageUrl(img, movie.getPoster(), Res.IMAGE_WIDTH_SMALL);
    tvTitle.setText(movie.getTitle());
    tvSubtitle.setText(movie.getSubtitle());
    tvDescription.setText(movie.getDescription());
  }
  
  @Override
  public int getItemCount() {
    return mMovies.size();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle;
    ImageView imgPoster;
    TextView tvSubtitle;
    TextView tvDescription;
    
    public ViewHolder(View itemView) {
      super(itemView);
      
      tvTitle = itemView.findViewById(R.id.tv_title);
      imgPoster = itemView.findViewById(R.id.image_view);
      tvSubtitle = itemView.findViewById(R.id.tv_subtitle);
      tvDescription = itemView.findViewById(R.id.tv_description);
    }
  }
  
  public interface AdapterCallback{
    void onClickView(View clickedView);
  }
}