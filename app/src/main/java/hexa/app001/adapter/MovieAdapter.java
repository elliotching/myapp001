package hexa.app001.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

import static androidx.constraintlayout.widget.Constraints.TAG;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
  
  private ArrayList<Movie> mMovies;
  private AppCompatActivity activity;
  private Context context;
  
  // Pass in the contact array into the constructor
  public MovieAdapter(AppCompatActivity activity, ArrayList<Movie> movies) {
    mMovies = movies;
    this.activity = activity;
  }
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    context = parent.getContext();
    
    LayoutInflater inflater = LayoutInflater.from(context);
    
    // Inflate the custom layout
    View contactView = inflater.inflate(R.layout.item_card_view, parent, false);
    
    // Return a new holder instance
    ViewHolder viewHolder = new ViewHolder(context, contactView);
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
    
//    img.post(new Runnable() {
//      @Override
//      public void run() {
//        int width = img.getMeasuredWidth();
//        Log.d(movie.getTitle(), "onBindViewHolder: layoutParams.width = "+img.getMeasuredWidth());
//        Log.d(movie.getTitle(),"imageview width:" + img.getWidth() + " height:" + img.getHeight());
//        img.getLayoutParams().height = (int) (width / 9.0 * 16.0);
////        img.requestLayout();
//
//      }
//    });
  }
  
  @Override
  public int getItemCount() {
    return mMovies.size();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvTitle;
    ImageView imgPoster;
    TextView tvSubtitle;
    TextView tvDescription;
    Context context;
    
    public ViewHolder(Context context, View itemView) {
      super(itemView);
      
      tvTitle = itemView.findViewById(R.id.tv_title);
      imgPoster = itemView.findViewById(R.id.image_view);
      tvSubtitle = itemView.findViewById(R.id.tv_subtitle);
      tvDescription = itemView.findViewById(R.id.tv_description);
      
      this.context = context;
      itemView.setOnClickListener(this);
    }
    
    // Handles the row being being clicked
    @Override
    public void onClick(View view) {
      int position = getAdapterPosition(); // gets item position
      if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
        Movie movie = mMovies.get(position);
        Toast.makeText(context, tvTitle.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ViewActivity.class);
        intent.putExtra(Res.INTENT_EXTRA_KEY_MOVIE, movie);
        activity.startActivityForResult(intent, Res.REQUEST_CODE_SELECT_CONTACT_REQUEST);
      }
  
      ViewGroup.LayoutParams layoutParams = imgPoster.getLayoutParams();
      Log.d(TAG, "onBindViewHolder: layoutParams.width = "+layoutParams.width);
      Log.d(TAG, "onBindViewHolder: layoutParams.height = "+layoutParams.height);
    }
  }
}