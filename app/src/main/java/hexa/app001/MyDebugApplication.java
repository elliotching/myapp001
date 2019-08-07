package hexa.app001;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyDebugApplication extends Application {
  
  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    
    RealmInspectorModulesProvider realmInspector = RealmInspectorModulesProvider.builder(this)
//                .withDeleteIfMigrationNeeded(true)
        .build();
    
    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(realmInspector)
        .build());
//        Stetho.initialize(
//            Stetho.newInitializerBuilder(this)
//                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                    .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                    .build());
  }
}
