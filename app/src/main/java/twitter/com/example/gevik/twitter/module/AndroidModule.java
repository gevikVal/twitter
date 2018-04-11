package twitter.com.example.gevik.twitter.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    private Context context;

    public AndroidModule(Application application){
        this.context = application;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
