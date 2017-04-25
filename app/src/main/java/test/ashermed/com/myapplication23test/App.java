package test.ashermed.com.myapplication23test;

import android.app.Application;
import android.content.Context;

import com.antfortune.freeline.FreelineCore;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by jiang on 2017/3/21.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
        refWatcher = LeakCanary.install(this);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);

    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;

    }

    private RefWatcher refWatcher;
}
