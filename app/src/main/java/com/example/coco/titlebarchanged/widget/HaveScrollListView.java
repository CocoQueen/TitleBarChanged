package com.example.coco.titlebarchanged.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by coco on 2017/12/6.
 */

public class HaveScrollListView extends ScrollView {


    private ScrollerViewListener listener;

    public HaveScrollListView(Context context) {
        super(context);
    }

    public HaveScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HaveScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollerViewListener(ScrollerViewListener listener) {
        this.listener = listener;
    }

    public interface ScrollerViewListener {
        void onScrollerChanged(HaveScrollListView mSc, int x, int y, int oldx, int oldy);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onScrollerChanged(this, l, t, oldl, oldt);
        }
    }
}
