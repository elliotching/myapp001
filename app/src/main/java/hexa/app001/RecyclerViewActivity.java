package hexa.app001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    static String ID = "id_tag";
    public final Context context = this;
//    public static String description(){
//        return context.getResources().getString(R.string.description);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        // ...
        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // Initialize contacts
        ArrayList<Contact> contacts = createContactsList(20);
        // Create adapter passing in the sample user data
        ContactsAdapter adapter = new ContactsAdapter(contacts);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

    public ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        int lastContactId = 0;
        String description = context.getResources().getString(R.string.description);
        for (int i = 1; i <= numContacts; i++) {
//            int id = ++lastContactId;
            int id = lastContactId;
            contacts.add(new Contact("Title " + id, i <= numContacts / 2, getImage(id), "Subtitle "+id+"\nAnother line\nYet another line", description));
            lastContactId++;
        }

        return contacts;
    }

    private static int getImage(int i) {
        int n = (i % 9) + 1;
        if(n==1){
            return R.drawable.s001;
        }
        if(n==2){
            return R.drawable.s002;
        }
        if(n==3){
            return R.drawable.s003;
        }
        if(n==4){
            return R.drawable.s004;
        }
        if(n==5){
            return R.drawable.s005;
        }
        if(n==6){
            return R.drawable.s006;
        }
        if(n==7){
            return R.drawable.s007;
        }
        if(n==8){
            return R.drawable.s008;
        }
        if(n==9){
            return R.drawable.s009;
        }
        return R.drawable.s007;
    }
}
