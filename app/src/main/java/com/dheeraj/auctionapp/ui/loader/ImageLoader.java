package com.dheeraj.auctionapp.ui.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.cache.MemoryCache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.WeakHashMap;

public class ImageLoader {

    MemoryCache<Bitmap> mMemoryCache = new MemoryCache();
    RequestQueueExecutorService mRequestService = new RequestQueueExecutorService();
    private Map<ImageView, String> mImageMap = new WeakHashMap<>();
    Handler handler = new Handler(); //Default constructor associates this handler with the Looper for the current thread.

    public void getImageBitmap(String url, ImageView imageView) {
        mImageMap.put(imageView, url);

        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        else {
            mRequestService.addToQueue(new ImageLoaderRunnable(new ImageLoadRequestHolder(url, imageView)));
        }
    }

    public static Bitmap findBitmapByURL(String name) {
        /*For this Demo, simply writing a coarse method to load Bitmap from local storage*/
        Bitmap bitmap = null;
        try {
            String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "cars" + "/" + name;
            //BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inPreferredConfig = Bitmap.Config.ARGB_8888; // I can optimise it further to use 565
            File newFile = new File(imagePath);
            //bitmap = BitmapFactory.decodeFile(imagePath, null);
            bitmap = BitmapFactory.decodeStream(new FileInputStream(newFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    class ImageLoaderRunnable implements Runnable {

        ImageLoadRequestHolder mHolder;

        ImageLoaderRunnable(ImageLoadRequestHolder holder) {
            mHolder = holder;
        }

        @Override
        public void run() {
           /*TODO: Will improve this logic to handle fast scroll and imageView Recycling */
            Bitmap bmp = findBitmapByURL(mHolder.mUrl);
            mMemoryCache.put(mHolder.mUrl, bmp);

            BitmapDisplayer bd = new BitmapDisplayer(bmp, mHolder);
            handler.post(bd);
            /*Will use Picaso someday :)*/
            //Activity a = (Activity) mHolder.mImageView.getContext(); //Hmmm not working. Fragment Fragment Fragment
            //a.runOnUiThread(bd);
        }

    }

    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        ImageLoadRequestHolder photoToLoad;

        public BitmapDisplayer(Bitmap b, ImageLoadRequestHolder p) {
            bitmap = b;
            photoToLoad = p;
        }

        public void run() {
            if (bitmap != null)
                photoToLoad.mImageView.setImageBitmap(bitmap);
            else
                photoToLoad.mImageView.setImageResource(R.drawable.img_not_found);
        }
    }

}
