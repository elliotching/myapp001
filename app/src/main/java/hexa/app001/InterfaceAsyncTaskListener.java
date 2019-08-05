package hexa.app001;

import java.util.ArrayList;

public interface InterfaceAsyncTaskListener {
    void onCompletedInitRecyclerView(ArrayList<Contact> al);
    void initData();

    // use in Edit Activity (Save)
    void onSaveCompletedInitRecyclerView();

    // use in Edit Activity (Select data and fill up all EditText's)
    void onSelectCompleted(Contact copiedContact);

    void onSaveCompletedRefreshViewActivity(Contact copiedContact);
}
