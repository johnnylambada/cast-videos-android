/*
 * Copyright (C) 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.sample.cast.refplayer.browser;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.sample.cast.refplayer.R;
import com.google.sample.cast.refplayer.utils.MediaItem;
import com.google.sample.cast.refplayer.utils.Utils;

import java.util.List;

/**
 * An {@link ArrayAdapter} to populate the list of videos.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private static final float mWidth = 114;
    private static final float mAspectRatio = 9f / 16f;
    private static Point mImageSize;
    private final ItemClickListener mClickListener;
    private List<MediaItem> videos;

    public VideoListAdapter(ItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        if (mImageSize==null) {
            mImageSize = new Point((int) Utils.d2p(context, mWidth), (int) Utils.d2p(context, mWidth * mAspectRatio));
        }
        View parent = LayoutInflater.from(context).inflate(R.layout.browse_row, viewGroup, false);
        return ViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final MediaItem item = videos.get(position);
        viewHolder.setTitle(item.getTitle());
        viewHolder.setDescription(item.getStudio());
        viewHolder.setImage(item.getImage(0));

        viewHolder.mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.itemClicked(v, item, position);
            }
        });

        viewHolder.mTextContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.itemClicked(v, item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos == null ? 0 : videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View mParent;
        private final View mTextContainer;
        private TextView mTitleView;
        private TextView mDescriptionView;
        private ImageView mImgView;

        public static ViewHolder newInstance(View parent) {
            ImageView imgView = (ImageView) parent.findViewById(R.id.imageView1);
            TextView titleView = (TextView) parent.findViewById(R.id.title);
            TextView descriptionView = (TextView) parent.findViewById(R.id.description);
            View textContainer = parent.findViewById(R.id.text_container);
            return new ViewHolder(parent, imgView, textContainer, titleView, descriptionView);
        }

        private ViewHolder(View parent, ImageView imgView, View textContainer, TextView titleView,
                TextView descriptionView) {
            super(parent);
            mParent = parent;
            mImgView = imgView;
            mTextContainer = textContainer;
            mTitleView = titleView;
            mDescriptionView = descriptionView;
        }

        public void setTitle(String title) {
            mTitleView.setText(title);
        }

        public void setDescription(String description) {
            mDescriptionView.setText(description);
        }

        public void setImage(String imgUrl) {
            Glide.with(mParent.getContext())
                    .load(imgUrl)
                    .override(mImageSize.x,mImageSize.y)
                    .into(mImgView);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            mParent.setOnClickListener(listener);
        }

        public ImageView getImageView() {
            return mImgView;
        }
    }

    public void setData(List<MediaItem> data) {
        videos = data;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {

        void itemClicked(View v, MediaItem item, int position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
