package test.ashermed.com.myapplication23test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import test.ashermed.com.myapplication23test.R;
import test.ashermed.com.myapplication23test.widget.CustomSwipeLayout;


/**
 * Created by jiang on 2017/3/24.
 */

public class Adapter extends RecyclerSwipeAdapter<Adapter.SwipeHolder> {

    private Context context;
    private int count = 28;
    public Adapter() {
    }

    public void  setAdapter(int count){
        this.count =count;
        notifyDataSetChanged();
    }





    @Override
    public SwipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new SwipeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SwipeHolder viewHolder, final int position) {
        viewHolder.swipe.addSwipeListener(new SimpleSwipeListener(){
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
//        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "hehe"+position, Toast.LENGTH_SHORT).show();
//                mItemManger.closeAllItems();
//            }
//        });
        viewHolder.swipe.setOnClickItemListener(new CustomSwipeLayout.OnClickItemListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "hehe"+position, Toast.LENGTH_SHORT).show();
                mItemManger.closeAllItems();
            }
        });

        viewHolder.tv.setText("11");

        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class SwipeHolder extends RecyclerView.ViewHolder {
        CustomSwipeLayout swipe;
        ImageView trash;
        LinearLayout linearLayout;
        TextView tv;
        public SwipeHolder(View itemView) {
            super(itemView);
            initView(itemView);

        }
        private void initView(View itemView) {
            swipe = (CustomSwipeLayout) itemView.findViewById(R.id.swipe);
            trash = (ImageView) itemView.findViewById(R.id.trash);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_content);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
