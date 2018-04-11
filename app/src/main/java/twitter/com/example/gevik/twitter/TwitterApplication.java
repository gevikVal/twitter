package twitter.com.example.gevik.twitter;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;
import twitter.com.example.gevik.twitter.Root.ApplicationModule;
import twitter.com.example.gevik.twitter.component.ApplicationComponent;
import twitter.com.example.gevik.twitter.component.DaggerApplicationComponent;
import twitter.com.example.gevik.twitter.module.AndroidModule;

/**
 * Created by gevik on 4/10/2018.
 */

public class TwitterApplication extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    private static TwitterApplication currentApplication = null;

    private RefWatcher refWatcher;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeTimber();
        initializeLeakCanary();


        currentApplication = this;

        applicationComponent = createApplicationComponent();


    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .androidModule(new AndroidModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static TwitterApplication  getInstance() {
        return currentApplication;
    }

    public static RefWatcher getRefWatcher(Context context) {
        TwitterApplication  application = (TwitterApplication ) context.getApplicationContext();
        return application.refWatcher;
    }

    private void initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {

            return;
        }
        refWatcher = LeakCanary.install(this);
    }



    private void initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                // Add the line number to the tag
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    private static class ReleaseTree extends Timber.Tree {

        private static final int MAX_LOG_LENGTH = 4000;

        @Override
        protected boolean isLoggable(String tag, int priority) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return false;
            }

            return true;
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (isLoggable(priority)) {

                if (message.length() < MAX_LOG_LENGTH) {
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, message);
                    } else {
                        Log.println(priority, tag, message);
                    }
                    return;
                }

                for (int i = 0, length = message.length(); i < length; i++) {
                    int newline = message.indexOf('\n', i);
                    newline = newline != -1 ? newline : length;
                    do {
                        int end = Math.min(newline, i + MAX_LOG_LENGTH);
                        String part = message.substring(i, end);
                        if (priority == Log.ASSERT) {
                            Log.wtf(tag, part);
                        } else {
                            Log.println(priority, tag, part);
                        }
                        i = end;
                    } while (i < newline);
                }
            }
        }
    }
}
