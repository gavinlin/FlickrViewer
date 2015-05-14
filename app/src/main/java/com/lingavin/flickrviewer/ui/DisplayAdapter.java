package com.lingavin.flickrviewer.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lingavin.flickrviewer.R;
import com.lingavin.flickrviewer.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gavin on 15/5/14.
 */
public class DisplayAdapter extends PagerAdapter {

    ArrayList<Photo> photos;

    public DisplayAdapter(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ImageView) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        ImageView imageView = new ImageView(context);

        Picasso.with(context).load(photos.get(position).getMedia().getM())
                .placeholder(R.drawable.default_image).into(imageView);
        ((ViewPager) container).addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
