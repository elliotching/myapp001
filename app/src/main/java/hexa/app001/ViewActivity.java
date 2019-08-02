package hexa.app001;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class ViewActivity extends AppCompatActivity {
    private AppCompatActivity activity = this;
    private Context context = this;
    private TextView titleTextView;
    private ImageView imageView;
    private TextView subtitleTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = activity.getIntent();
        final long contactId = intent.getLongExtra(Res.INTENT_EXTRA_KEY_CLICKED_ID, -1);
        AsyncSelectContact async = new AsyncSelectContact();
        async.execute(contactId);

        FloatingActionButton editFab = findViewById(R.id.fab_edit);
        titleTextView = findViewById(R.id.contact_title);
        imageView = findViewById(R.id.contact_image);
        subtitleTextView = findViewById(R.id.contact_subtitle);
        descriptionTextView = findViewById(R.id.contact_description);


        editFab.setOnClickListener(view -> {
            // to EDIT!
            Intent intent1 = new Intent(context, EditActivity.class);
            intent1.putExtra(Res.INTENT_EXTRA_EDIT_CONTACT_ID, contactId);
            intent1.putExtra(Res.INTENT_EXTRA_STATUS_CODE, 1);
            startActivityForResult(intent1, Res.EDIT_CONTACT_REQUEST);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            activity.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == Res.EDIT_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                long editingId = data.getLongExtra(Res.INTENT_EXTRA_EDITED_CONTACT_ID, 34895);

                AsyncUpdateContact async = new AsyncUpdateContact();
                async.execute(editingId);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class AsyncUpdateContact extends AsyncTask<Long, Void, Void> {

        // execute() >>> init asynctask
        // AsyncTask<Param, Progress, Result>
        // doInBackground, end return must be going >>> Post (RESULT Type)
        // doInBackground, mid publishProg must be going >>> ProgUpd (PROGRESS Type)

        private Realm realm;
        private Contact copiedContact;

        @Override
        protected Void doInBackground(Long[] params) {
            this.realm = Realm.getInstance(Res.realmConfig());
            realm.executeTransaction(realm1 -> {
                Contact readContact = realm1.where(Contact.class).equalTo("id", params[0]).findFirst();
                if(readContact != null) {
                    copiedContact = realm1.copyFromRealm(readContact);
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (copiedContact.getTitle() != null) {
                titleTextView.setText(copiedContact.getTitle());
                subtitleTextView.setText(copiedContact.getSubtitle());
                descriptionTextView.setText(copiedContact.getDesc());
                Picasso.get()
                        .load(copiedContact.getImage())
                        .resize(1280, 0)
                        .placeholder(R.drawable.ic_pending)
                        .error(R.drawable.ic_broken)
                        .into(imageView);
            }
        }
    }



    private class AsyncSelectContact extends AsyncTask<Long, Void, Void> {
        private Realm realm;
        private Contact copiedContact;
        @Override
        protected Void doInBackground(Long[] params) {
            this.realm = Realm.getInstance(Res.realmConfig());

            realm.executeTransaction(realm1 -> {
                Realm realm = Realm.getInstance(Res.realmConfig());
                Contact contact = realm.where(Contact.class).equalTo("id", params[0]).findFirst();
                copiedContact = realm1.copyFromRealm(contact);
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (copiedContact != null) {
                activity.getSupportActionBar().setTitle(copiedContact.getTitle());
                titleTextView.setText(copiedContact.getTitle());
                Picasso.get()
                        .load(copiedContact.getImage())
                        .resize(1280, 0)
                        .placeholder(R.drawable.ic_pending)
                        .error(R.drawable.ic_broken)
                        .into(imageView);
                subtitleTextView.setText(copiedContact.getSubtitle());
                descriptionTextView.setText(copiedContact.getDesc());
            }
        }
    }
}







