package hexa.app001;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = activity.getIntent();
        Contact contact = intent.getParcelableExtra(RecyclerViewActivity.ID);
        this.getSupportActionBar().setTitle(contact.getTitle());

        TextView titleTextView = findViewById(R.id.contact_title);
        ImageView imageView = findViewById(R.id.contact_image);
        TextView subtitleTextView = findViewById(R.id.contact_subtitle);
        TextView descriptionTextView = findViewById(R.id.contact_description);

        titleTextView.setText(contact.getTitle());
        imageView.setImageResource(contact.getImage());
        subtitleTextView.setText(contact.getSubtitle());
        descriptionTextView.setText(contact.getDesc());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            activity.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
