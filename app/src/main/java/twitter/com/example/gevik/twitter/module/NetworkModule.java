package twitter.com.example.gevik.twitter.module;

import android.app.Application;
import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;
import twitter.com.example.gevik.twitter.api.TwitterApiServiceToken;
import twitter.com.example.gevik.twitter.scope.ApplicationScope;

/**
 * Created by gevik on 4/10/2018.
 */
@Module
public class NetworkModule {


    @Provides
    public Cache provideCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 11MB
        Cache cache = null;
        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(application.getCacheDir(), "http");
            cache = new Cache(cacheDir, cacheSize);
        } catch (Exception e) {
            Timber.e(e, "Unable to install disk cache.");
        }
        return cache;
    }

    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //  if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //  } else {
        //    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        // }
        return httpLoggingInterceptor;
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Cache cache , HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.cache(cache);

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        return okHttpClientBuilder.build();
    }



    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @ApplicationScope
    public TwitterApiServiceToken provideTwitterService(Retrofit retrofit) {
        return retrofit.create(TwitterApiServiceToken.class);
    }

}
