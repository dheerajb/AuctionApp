package com.dheeraj.auctionapp.ui.loader;

import android.widget.ImageView;

public class ImageLoadRequestHolder implements LoadRequestHolder {
    public String mUrl;
    public ImageView mImageView;

    public ImageLoadRequestHolder(String url, ImageView imageView){
        mUrl = url;
        mImageView = imageView;
    }
}
