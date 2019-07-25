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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {


    private List<Contact> mContacts;

    // Pass in the contact array into the constructor
    public ContactsAdapter(List<Contact> contacts) {
        mContacts = contacts;
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
        Contact contact = mContacts.get(position);

        TextView titleTextView = holder.titleTextView;
        ImageView img = holder.imgView;
        TextView subtitleTextView = holder.subtitleTextView;
        TextView descriptionTextView = holder.descriptionTextView;

        titleTextView.setText(contact.getTitle());
        img.setImageResource(contact.getImage());
        subtitleTextView.setText(contact.getSubtitle());
        descriptionTextView.setText(contact.getDesc());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView titleTextView;
        public ImageView imgView;
        public TextView subtitleTextView;
        public TextView descriptionTextView;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.contact_title);
            imgView = itemView.findViewById(R.id.image_view);
            subtitleTextView = itemView.findViewById(R.id.contact_subtitle);
            descriptionTextView = itemView.findViewById(R.id.contact_description);

            this.context = context;

            itemView.setOnClickListener(this);
        }
        // Handles the row being being clickeda
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Contact contact = mContacts.get(position);
                Toast.makeText(context, titleTextView.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra(RecyclerViewActivity.ID, contact);
                context.startActivity(intent);
            }
        }
    }
}