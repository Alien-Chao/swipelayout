package test.ashermed.com.myapplication23test.singleton;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jiang on 2017/3/27.
 */

public   class Singleton {
    public Singleton(Context context) {
        this.context = context;
    }

    String a = "";

    private static Context context;
    private static Singleton singleton;

    public static Singleton  getSingleton(Context context) {
        if (singleton == null) {
            singleton = new Singleton(context);
        }
          return singleton;
    }

    public static   void show(){
        Toast.makeText(context, "hehehe", Toast.LENGTH_SHORT).show();

    }


}
