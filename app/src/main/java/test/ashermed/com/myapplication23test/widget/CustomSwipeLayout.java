package test.ashermed.com.myapplication23test.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.daimajia.swipe.SwipeLayout;

/**
 * Created by jiang on 2017/3/27.
 */

public class CustomSwipeLayout extends SwipeLayout {

    float positionX, positionY;

    OnClickItemListener onClickItemListener;
    public CustomSwipeLayout(Context context) {
        super(context);
    }

    public CustomSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSwipeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int eventMask = MotionEventCompat.getActionMasked(ev);
        int action =ev.getAction();
        switch (eventMask) {
            case MotionEvent.ACTION_DOWN:
                positionY = ev.getRawY();
                positionX = ev.getRawX();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (positionY == ev.getRawY() && positionX == ev.getRawX()) {
                    if(onClickItemListener!=null)
                        onClickItemListener.onClick(CustomSwipeLayout.this);
                }
                break;

        }

        return super.onTouchEvent(ev);
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public interface OnClickItemListener {
        void onClick(View view);
    }

}
