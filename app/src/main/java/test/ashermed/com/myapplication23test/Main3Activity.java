package test.ashermed.com.myapplication23test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.util.Attributes;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.leakcanary.RefWatcher;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import test.ashermed.com.myapplication23test.adapter.Adapter;
import test.ashermed.com.myapplication23test.adapter.DividerItemDecoration;
import test.ashermed.com.myapplication23test.singleton.Singleton;


public class Main3Activity extends AppCompatActivity {

    private XRecyclerView rv;
    private ImageView iv;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //在自己的应用初始Activity中加入如下两行代码
        RefWatcher refWatcher = App.getRefWatcher(this);
        refWatcher.watch(this);

        Singleton singleton = Singleton.getSingleton(this);
        singleton.show();

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main3Activity.this, "hahhahaha", Toast.LENGTH_SHORT).show();
            }
        },10000);
        initView();
        YoYo.with(Techniques.Tada).duration(2000).delay(100).playOn(iv);
       // finish();
    }

    int count=0;
    private void initView() {
        rv = (XRecyclerView) findViewById(R.id.rv);
        iv = (ImageView) findViewById(R.id.iv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        // Item Decorator:
        // rv.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
//        rv.setItemAnimator(new DefaultItemAnimator());
        // Item Decorator:  new DividerItemDecoration(this.getResources().getDrawable(R.drawable.divider))
        rv.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
        rv.setItemAnimator(new FadeInLeftAnimator());
        adapter = new Adapter();
        ((Adapter) adapter).setMode(Attributes.Mode.Single);
        rv.setAdapter(adapter);
        rv.refresh();

        rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rv.setArrowImageView(R.drawable.ic_dashboard_black_24dp);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rv.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((Adapter) adapter).setAdapter(count+=50);
                        rv.loadMoreComplete();
                    }
                },1000);
            }
        });
    }
}
