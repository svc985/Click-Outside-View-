package org.prikic.clicktouchlisteners;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnTouchOutsideViewListener {

    TextView txt;
    LinearLayout popoverLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);
        popoverLayout = findViewById(R.id.popover);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popoverLayout.setVisibility(View.VISIBLE);
            }
        });

        setOnTouchOutsideViewListener(popoverLayout, this);
    }

    @Override
    public void onTouchOutside(View view, MotionEvent event) {
        popoverLayout.setVisibility(View.GONE);
    }

    public void testClick(View view) {
        Log.d("test", "test click");
    }

    public void dismissPopover(View view) {
        popoverLayout.setVisibility(View.GONE);
    }

    // detect click outside view methods
    private View touchOutsideView;

    private OnTouchOutsideViewListener onTouchOutsideViewListener;

    public void setOnTouchOutsideViewListener(View view, OnTouchOutsideViewListener onTouchOutsideViewListener) {
        touchOutsideView = view;
        this.onTouchOutsideViewListener = onTouchOutsideViewListener;
    }

    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // Notify touch outside listener if user tapped outside a given view
            if (onTouchOutsideViewListener != null && touchOutsideView != null
                    && touchOutsideView.getVisibility() == View.VISIBLE) {
                Rect viewRect = new Rect();
                touchOutsideView.getGlobalVisibleRect(viewRect);
                if (!viewRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    onTouchOutsideViewListener.onTouchOutside(touchOutsideView, ev);
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
