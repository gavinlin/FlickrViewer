package com.lingavin.flickrviewer.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lingavin.flickrviewer.R;
import com.lingavin.flickrviewer.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gavin on 15/5/14.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Photo> photos;
    private Context context;
    private OnItemClickListener mListener;
    private int selectedNum = -1;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    PhotosAdapter (List<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
        selectedNum = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Photo photo = photos.get(position);
        ImageView imageView = holder.imageView;

        Picasso.with(context).load(photos.get(position).getMedia().getM())
                .resize(96, 96).centerCrop()
                .placeholder(R.drawable.default_image).into(imageView);
        if (position == selectedNum) {
            imageView.setActivated(true);
        } else {
            imageView.setActivated(false);
        }
        if (mListener != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, position);
                    selectedNum = position;
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setSelectedNum(int num) {
        selectedNum = num;
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(
                    R.id.main_recycler_item_image_view);
        }
    }
}
