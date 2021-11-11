package com.arshad.dariohealthassignment.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;


public class ImageCache {

    private static final int MAX_SERIAL_THREAD_POOL_SIZE = 1;
    private static final int MAX_PARALLEL_THREAD_POOL_SIZE = 50;

    private static final int MAX_CACHE_SIZE = 10 * 1024 * 1024; //10 MB

    private static final String TAG = ImageCache.class.getSimpleName();
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10;
    private static final int IMAGE_CACHE_QUALITY = 90;

    private static ImageCache instance;

    private final Context context;
    private final ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;
    private final RequestQueue mImageRequestQueue;

    public static ImageCache get() {
        if(instance == null) {
            throw new RuntimeException("Call ApiService.init() first");
        }

        return instance;
    }

    public static ImageCache init(Context context) {
        instance = new ImageCache(context);
        return instance;
    }

    public ImageCache(Context context) {
        this.context = context;

        mImageRequestQueue = prepareParallelRequestQueue();
        mImageRequestQueue.start();

        mImageLoader = new ImageLoader(mImageRequestQueue,
                new DiskLruImageCache(context, this.getClass().getSimpleName(),
                        DISK_CACHE_SIZE, Bitmap.CompressFormat.JPEG, IMAGE_CACHE_QUALITY));

    }

    private RequestQueue prepareParallelRequestQueue() {
        Cache cache = new DiskBasedCache(context.getCacheDir(), MAX_CACHE_SIZE);
        Network network = getNetwork();
        return new RequestQueue(cache, network, MAX_PARALLEL_THREAD_POOL_SIZE);
    }

    private RequestQueue prepareSerialRequestQueue() {
        Cache cache = new DiskBasedCache(context.getCacheDir(), MAX_CACHE_SIZE);
        Network network = getNetwork();
        return new RequestQueue(cache, network, MAX_SERIAL_THREAD_POOL_SIZE);
    }

    private Network getNetwork() {
        HttpStack stack;
        String userAgent = "volley/0";
        if(Build.VERSION.SDK_INT >= 9) {
            stack = new HurlStack();
        } else {
            throw new RuntimeException("SDK version <=9 is not supported");
        }
        return new BasicNetwork(stack);
    }
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
