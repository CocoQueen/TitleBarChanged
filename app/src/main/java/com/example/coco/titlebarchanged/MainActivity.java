package com.example.coco.titlebarchanged;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coco.titlebarchanged.utils.StatusBarUtils;
import com.example.coco.titlebarchanged.widget.HaveScrollListView;

public class MainActivity extends AppCompatActivity implements HaveScrollListView.ScrollerViewListener {

    private HaveScrollListView mHsc;
    private ListView lv;
    private TextView mTv;
    private int height;
    private ImageView mImgBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtils.setImgTransparent(this);
        setContentView(R.layout.activity_main);

        mHsc = findViewById(R.id.mSc);
        mTv = findViewById(R.id.mTv);
        mImgBanner = findViewById(R.id.mImg_banner);
        lv = findViewById(R.id.mNoLv);

        mImgBanner.setFocusable(true);
        mImgBanner.setFocusableInTouchMode(true);
        mImgBanner.requestFocus();

        initListener();
        initDate();


    }

    private void initDate() {
        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        lv.setAdapter(adapter);
    }

    private void initListener() {
        ViewTreeObserver observer = mImgBanner.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = mImgBanner.getHeight();
                mHsc.setScrollerViewListener(MainActivity.this);
            }
        });
    }

    @Override

    public void onScrollerChanged(HaveScrollListView mSc, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            mTv.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));
            mTv.setText("");
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            mTv.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            mTv.setBackgroundColor(Color.argb((int) alpha, 144, 151, 166));
            mTv.setText("标题栏");
        } else {    //滑动到banner下面设置普通颜色
            mTv.setBackgroundColor(Color.argb((int) 255, 144, 151, 166));
        }
    }
}
