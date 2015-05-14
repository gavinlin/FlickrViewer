package com.lingavin.flickrviewer;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by gavin on 15/5/14.
 */
public class FlickrApplication extends Application{
    private RequestQueue mRequestQueue;
    private static FlickrApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .denyCacheImageMultipleSizesInMemory()
                .build();
        ImageLoader.getInstance().init(config);

    }

    public static synchronized FlickrApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
