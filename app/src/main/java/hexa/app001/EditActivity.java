package hexa.app001;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import hexa.app001.databinding.ActivityEditBinding;
import io.realm.Realm;
import io.realm.RealmResults;

public class EditActivity extends AppCompatActivity {
    private AppCompatActivity activity = this;
    private Context context = this;
    private static int status = 0; // 0 = ADD NEW , 1 = EDIT
    private ActivityEditBinding binding;
    private long contactId;

    @BindView(R.id.edit_title)
    EditText editTitle;

    @BindView(R.id.edit_subtitle)
    EditText editSubtitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_edit);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        Intent intent = activity.getIntent();
        contactId = intent.getLongExtra(Res.INTENT_EXTRA_EDIT_CONTACT_ID, -1);
        status = intent.getIntExtra(Res.INTENT_EXTRA_STATUS_CODE, -1);

        AsyncSelectContact async = new AsyncSelectContact();
        async.execute(contactId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        activity.getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            activity.finish();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_save) {
            AsyncSaveContact async = new AsyncSaveContact();
            async.execute(contactId);

            Intent intent = new Intent();
            intent.putExtra(Res.INTENT_EXTRA_EDITED_CONTACT_ID, contactId);
            activity.setResult(AppCompatActivity.RESULT_OK, intent);
            activity.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class AsyncSelectContact extends AsyncTask<Long, Void, Void> {
        private Realm realm;
        private Contact copiedContact;
//        private long editingId;
        @Override
        protected Void doInBackground(Long[] params) {
            this.realm = Realm.getInstance(Res.realmConfig());

            realm.executeTransaction(realm1 -> {
                Contact contact = realm1.where(Contact.class).equalTo("id", params[0]).findFirst();
                if (contact != null) {
                    copiedContact = realm1.copyFromRealm(contact);
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // From VIEW or from LIST
            // click on VIEW -> EDIT
            // click on LIST -> ADD NEW

            if(copiedContact == null){
                status = 0;
                activity.getSupportActionBar().setTitle(R.string.new_item);
            }else{
                status = 1;
                activity.getSupportActionBar().setTitle(Res.get(context, R.string.title_editing)+copiedContact.getTitle());
                binding.setContact(copiedContact);
                Picasso.get()
                        .load(copiedContact.getImage())
                        .resize(1280, 0)
                        .placeholder(R.drawable.ic_pending)
                        .error(R.drawable.ic_broken)
                        .into(binding.imagePreview);
            }

            binding.editSubtitle.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence q, int s, int c, int a) {
                }

                public void onTextChanged(CharSequence q, int s, int b, int c) {
                    int L = binding.editSubtitle.getLineCount();
                    if(L > 3){
                        binding.editSubtitle.getText().delete(binding.editSubtitle.getSelectionEnd() - 1, binding.editSubtitle.getSelectionStart());
                    }
                }
            });
            binding.editImage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        if(binding.editImage.getText().toString().isEmpty()){
                            binding.editImage.setText(Res.DUMP_LINK);
                        }
                        Picasso.get()
                                .load(binding.editImage.getText().toString())
                                .resize(1280, 0)
                                .placeholder(R.drawable.ic_pending)
                                .error(R.drawable.ic_broken)
                                .into(binding.imagePreview);
                    }
                }
            });

        }
    }


    private class AsyncSaveContact extends AsyncTask<Long, Void, Void> {
        private Realm realm;
        private long editingId;
        @Override
        protected Void doInBackground(Long[] params) {
            this.realm = Realm.getInstance(Res.realmConfig());
            editingId = params[0];
            realm.executeTransaction(realm1 -> {
                if(status == 1) {
                    Contact contact = realm1.where(Contact.class).equalTo("id", params[0]).findFirst();
                    contact.setTitle(binding.editTitle.getText().toString());
                    contact.setSubtitle(binding.editSubtitle.getText().toString());
                    contact.setImage(binding.editImage.getText().toString());
                    contact.setDesc(binding.editDescription.getText().toString());
                }

                if(status == 0){
                    RealmResults<Contact> contacts = realm1.where(Contact.class).findAll();
                    int id = contacts.size() + 1;
                    Contact contact = realm1.createObject(Contact.class, id);
                    contact.setTitle(binding.editTitle.getText().toString());
                    contact.setSubtitle(binding.editSubtitle.getText().toString());
                    contact.setImage(binding.editImage.getText().toString().isEmpty()?Res.DUMP_LINK:binding.editImage.getText().toString());
                    contact.setDesc(binding.editDescription.getText().toString());
                }
            });
            return null;
        }
    }
}













