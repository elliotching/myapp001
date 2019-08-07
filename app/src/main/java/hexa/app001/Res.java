package hexa.app001;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Res {
  public static final int REQUEST_CODE_EDIT_CONTACT_REQUEST = 100;
  public static final String INTENT_EXTRA_EDITED_CONTACT_ID = "edited_contact_id";
  public static final String INTENT_EXTRA_KEY_CLICKED_ID = "clicked_id";//only passed "long" type id
  public static final String INTENT_EXTRA_EDIT_CONTACT_ID = "contact";
  public static final String INTENT_EXTRA_STATUS_CODE = "status of modifying";
  public static final String DUMP_LINK = "https://picsum.photos/id/0/5616/3744";
  public static final Integer ASYNC_CODE_SELECT_ALL_INIT_RV = 0;
  public static final Integer ASYNC_CODE_INIT_DATA = 1;
  public static final Integer ASYNC_CODE_SELECT_ALL = 2;
  public static final Integer ASYNC_CODE_CLEAR_ALL = 3;
  public static final long DEFAULT_FALSE = -1;
  public static final int REQUEST_CODE_SELECT_CONTACT_REQUEST = 101;
  
  public static final String get(Context context, int ResourceID) {
    return context.getResources().getString(ResourceID);
  }
  
  public static final RealmConfiguration realmConfig() {
    RealmConfiguration config = new RealmConfiguration
        .Builder()
        .deleteRealmIfMigrationNeeded()
        .build();
    return config;
  }
  
  public static String getImage(int i) {
    int n = (i % 9) + 1;
    return "https://picsum.photos/id/0/5616/3744";
  }
  
}
