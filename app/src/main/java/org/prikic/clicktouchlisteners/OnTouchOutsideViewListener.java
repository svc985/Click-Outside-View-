package org.prikic.clicktouchlisteners;

import android.view.MotionEvent;
import android.view.View;

public interface OnTouchOutsideViewListener {
    void onTouchOutside(View view, MotionEvent event);
}
