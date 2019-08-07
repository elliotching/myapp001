package hexa.app001;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MovieAdapter extends
    RecyclerView.Adapter<MovieAdapter.ViewHolder> {
  
  private ArrayList<Movie> mMovies;
  private AppCompatActivity activity;
  
  // Pass in the contact array into the constructor
  public MovieAdapter(AppCompatActivity activity, ArrayList<Movie> movies) {
    mMovies = movies;
    this.activity = activity;
  }
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    
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
    
    TextView titleTextView = holder.titleTextView;
    ImageView img = holder.imgView;
    TextView subtitleTextView = holder.subtitleTextView;
    TextView descriptionTextView = holder.descriptionTextView;
    
    titleTextView.setText(movie.getTitle());
    Picasso.get()
        .load(movie.getImage())
        .resize(100, 0)
        .placeholder(R.drawable.ic_pending)
        .error(R.drawable.ic_broken)
        .into(img);
    subtitleTextView.setText(movie.getSubtitle());
    descriptionTextView.setText(movie.getDesc());
  }
  
  @Override
  public int getItemCount() {
    return mMovies.size();
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView titleTextView;
    public ImageView imgView;
    public TextView subtitleTextView;
    public TextView descriptionTextView;
    private Context context;
    
    public ViewHolder(Context context, View itemView) {
      super(itemView);
      
      titleTextView = itemView.findViewById(R.id.tv_title);
      imgView = itemView.findViewById(R.id.image_view);
      subtitleTextView = itemView.findViewById(R.id.tv_subtitle);
      descriptionTextView = itemView.findViewById(R.id.tv_description);
      
      this.context = context;
      itemView.setOnClickListener(this);
    }
    
    // Handles the row being being clicked
    @Override
    public void onClick(View view) {
      int position = getAdapterPosition(); // gets item position
      if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
        Movie movie = mMovies.get(position);
        Toast.makeText(context, titleTextView.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ViewActivity.class);
        intent.putExtra(Res.INTENT_EXTRA_KEY_CLICKED_ID, movie.getId());
        activity.startActivityForResult(intent, Res.REQUEST_CODE_SELECT_CONTACT_REQUEST);
      }
    }
  }
}