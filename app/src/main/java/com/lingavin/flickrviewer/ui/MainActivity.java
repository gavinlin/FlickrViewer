package com.lingavin.flickrviewer.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lingavin.flickrviewer.FlickrApplication;
import com.lingavin.flickrviewer.R;
import com.lingavin.flickrviewer.model.Photo;
import com.lingavin.flickrviewer.utils.Constants;
import com.lingavin.flickrviewer.utils.FlickrJsonParser;
import com.lingavin.flickrviewer.utils.MLog;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG_STRING_OBJ = "string_req";
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mPhotoview;
    private ViewPager mDisplayView;
    private ArrayList<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        intViews();
        fetchPhotos();
    }

    /**
     * use toolbar to replace default action bar
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void intViews() {
        mPhotoview = (RecyclerView)findViewById(R.id.main_recycler_view);
        mDisplayView = (ViewPager)findViewById(R.id.main_display_view_pager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //set recycler view's orientation to horizontal
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mPhotoview.setLayoutManager(linearLayoutManager);
    }

    /**
     * fetch json from flickr and parse it
     */
    private void fetchPhotos() {
        StringRequest strReq = new StringRequest(Request.Method.GET,
                Constants.STR_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                photos = new ArrayList<>();
                photos.addAll(FlickrJsonParser.parsePhotos(response));
                MLog.i(TAG, photos.toString());
                PhotosAdapter adapter = new PhotosAdapter(photos, MainActivity.this);
                adapter.setOnItemClickListener(new PhotosAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mDisplayView.setCurrentItem(position);
                    }
                });
                mPhotoview.setAdapter(adapter);

                mDisplayView.setAdapter(new DisplayAdapter(photos));
                mDisplayView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, R.string.fetch_error, Toast.LENGTH_SHORT).show();
            }
        });
        strReq.setTag(TAG_STRING_OBJ);
        FlickrApplication.getInstance().getRequestQueue().add(strReq);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            fetchPhotos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
