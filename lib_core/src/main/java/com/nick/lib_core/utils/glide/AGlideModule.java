package com.nick.lib_core.utils.glide;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Nick in 2020/10/28 20:44
 * Des:
 */
@GlideModule
public class AGlideModule extends AppGlideModule {

    private static final String TAG = AGlideModule.class.getSimpleName();

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();
        //recommended memory cache size for the device
        Log.d(TAG, "CacheSize===" + calculator.getMemoryCacheSize());
        int cacheSize = calculator.getMemoryCacheSize();
        builder.setMemoryCache(new LruResourceCache(cacheSize));  //指定内存缓存大小
        // 指定位置在packageName/cache/glide_cache,大小为cacheSize的磁盘缓存
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide_cache", cacheSize));
        //设置BitmapPool缓存内存大小
        builder.setBitmapPool(new LruBitmapPool(cacheSize));
        //设置解码格式RGB_565，该格式解码的Bitmap不支持透明度
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565).disallowHardwareConfig()
        );
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        //设置使用okhttp加载
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.MINUTES).readTimeout(10, TimeUnit.MINUTES).retryOnConnectionFailure(true).build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    //禁用清单解析，以避免重复添加类似的模块
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}
