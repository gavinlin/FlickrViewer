package com.lingavin.flickrviewer.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by gavin on 15/5/14.
 */
public class DisplayUtil {
    public static float dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, r.getDisplayMetrics());
        return px;
    }
}
