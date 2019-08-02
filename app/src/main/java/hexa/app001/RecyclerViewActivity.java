package hexa.app001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final Integer ASYNC_CODE_SELECT_ALL_INIT_RV = 834;
    private static final Integer ASYNC_CODE_INIT_DATA = 238;
    private static final Integer ASYNC_CODE_SELECT_ALL_ON_CREATE = 3895;
    private static final Integer ASYNC_CODE_CLEAR_ALL = 1245;
    public final Context context = this;
    public final AppCompatActivity activity = this;
    private RecyclerView rvContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rvContacts = findViewById(R.id.rvContacts);
        FloatingActionButton fab = findViewById(R.id.fab_add);

        AsyncRealmTask async = new AsyncRealmTask();
        async.execute(RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_ON_CREATE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to Add!
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra(Res.INTENT_EXTRA_STATUS_CODE, 0);
                startActivity(intent);
            }
        });
    }

    private void clearAll() {
        AsyncRealmTask async = new AsyncRealmTask();
        async.execute(ASYNC_CODE_CLEAR_ALL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncRealmTask async = new AsyncRealmTask();
        async.execute(RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_INIT_RV);
    }

    private void initData(){
        AsyncRealmTask async = new AsyncRealmTask();
        async.execute(RecyclerViewActivity.ASYNC_CODE_INIT_DATA);
    }

    private static String getImage(int i) {
        int n = (i % 9) + 1;
        return "https://picsum.photos/id/0/5616/3744";
    }

    private class AsyncRealmTask extends AsyncTask<Integer, Void, Integer> {

        // execute() >>> init asynctask
        // AsyncTask<Param, Progress, Result>
        // doInBackground, end return must be going >>> Post (RESULT Type)
        // doInBackground, mid publishProg must be going >>> ProgUpd (PROGRESS Type)

        private Realm realm;
        private ArrayList<Contact> al;
        private RealmResults<Contact> contacts;

        @Override
        protected Integer doInBackground(Integer[] params) {
            this.realm = Realm.getInstance(Res.realmConfig());

            if(params[0].equals(RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_INIT_RV)) {
                realm.executeTransaction(realm1 -> {
                    contacts = this.realm.where(Contact.class).findAll();
                    al = new ArrayList<>();
                    al.addAll(this.realm.copyFromRealm(contacts));
                });
                return RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_INIT_RV;
            }

            if(params[0].equals(RecyclerViewActivity.ASYNC_CODE_INIT_DATA)) {
                int maxCount = 20;
                String description = context.getResources().getString(R.string.description);
                realm.executeTransaction(realm1 -> {
                    for (int id = 1; id <= maxCount; id++) {
                        final Contact contact = realm1.createObject(Contact.class, id);
                        contact.setTitle("Title " + id);
                        contact.setSubtitle("Subtitle "+id+"\nAnother line"+id+"\nYet another line"+id);
                        contact.setDesc(description);
                        contact.setImage(getImage(id));
                    }
                });
                return RecyclerViewActivity.ASYNC_CODE_INIT_DATA;
            }

            if(params[0].equals(RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_ON_CREATE)){
                realm.executeTransaction(realm1 -> {
                    contacts = this.realm.where(Contact.class).findAll();
                    al = new ArrayList<>();
                    al.addAll(this.realm.copyFromRealm(contacts));
                });
                return RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_ON_CREATE;
            }

            if(params[0].equals(RecyclerViewActivity.ASYNC_CODE_CLEAR_ALL)){
                realm.executeTransaction(realm->{
                    realm.delete(Contact.class);
                });
                return RecyclerViewActivity.ASYNC_CODE_CLEAR_ALL;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            if(result.equals(RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_INIT_RV)) {
                ContactsAdapter adapter = new ContactsAdapter(activity, al);
                rvContacts.setAdapter(adapter);
                rvContacts.setLayoutManager(new LinearLayoutManager(context));
            }

            if(result.equals(RecyclerViewActivity.ASYNC_CODE_SELECT_ALL_ON_CREATE)){
                if (al == null || al.size() == 0) {
                    initData();
                }
            }
        }
    }
}
