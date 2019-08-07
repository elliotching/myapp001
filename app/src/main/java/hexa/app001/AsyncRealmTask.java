package hexa.app001;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class AsyncRealmTask {
  
  // execute() >>> init asynctask
  // AsyncTask<Param, Progress, Result>
  // doInBackground, end return must be going >>> Post (RESULT Type)
  // doInBackground, mid publishProg must be going >>> ProgUpd (PROGRESS Type)
  
//  private Realm realm;
//  private ArrayList<Movie> al;
//  private RealmResults<Movie> movies;
//  private Context context;
//  private InterfaceAsyncTaskListener listener;
//
//  AsyncRealmTask(Context context, InterfaceAsyncTaskListener listener) {
//    this.context = context;
//    this.listener = listener;
//  }
//
//  @Override
//  protected Integer doInBackground(Integer[] params) {
//    this.realm = Realm.getInstance(Res.realmConfig());

//        if(params[0].equals(Res.ASYNC_CODE_SELECT_ALL_INIT_RV)) {
//            realm.executeTransaction(realm1 -> {
//                movies = realm1.where(Movie.class).findAll();
//                al = new ArrayList<>();
//                al.addAll(realm1.copyFromRealm(movies));
//            });
//            return Res.ASYNC_CODE_SELECT_ALL_INIT_RV;
//        }
    
//    if (params[0].equals(Res.ASYNC_CODE_INIT_DATA)) {
//      final int maxCount = 20;
//      String description = context.getResources().getString(R.string.description);
//      realm.executeTransaction(realm1 -> {
//        for (int id = 1; id <= maxCount; id++) {
//          final Movie movie = realm1.createObject(Movie.class, id);
//          movie.setTitle("Title " + id);
//          movie.setSubtitle("Subtitle " + id + "\nAnother line" + id + "\nYet another line" + id);
//          movie.setDesc(description);
//          movie.setImage(Res.getImage(id));
//        }
//        movies = realm1.where(Movie.class).findAll();
//        al = new ArrayList<>();
//        al.addAll(realm1.copyFromRealm(movies));
//      });
//      return Res.ASYNC_CODE_INIT_DATA;
//    }
    
//    if (params[0].equals(Res.ASYNC_CODE_SELECT_ALL)) {
//      realm.executeTransaction(realm1 -> {
//        movies = realm1.where(Movie.class).findAll();
//        al = new ArrayList<>();
//        al.addAll(realm1.copyFromRealm(movies));
//      });
//      return Res.ASYNC_CODE_SELECT_ALL;
//    }
    
//    if (params[0].equals(Res.ASYNC_CODE_CLEAR_ALL)) {
//      realm.executeTransaction(realm1 -> {
//        realm1.delete(Movie.class);
//      });
//      return Res.ASYNC_CODE_CLEAR_ALL;
//    }
//    return null;
//  }
//
//  @Override
//  protected void onPostExecute(Integer result) {
//    super.onPostExecute(result);
//    if (result.equals(Res.ASYNC_CODE_INIT_DATA)) {
//      listener.onCompletedInitRecyclerView(al);
//    }
//
//    if (result.equals(Res.ASYNC_CODE_SELECT_ALL)) {
//      if (al == null || al.size() == 0) {
//        listener.initData();
//      } else {
//        listener.onCompletedInitRecyclerView(al);
//      }
//    }
//  }
}