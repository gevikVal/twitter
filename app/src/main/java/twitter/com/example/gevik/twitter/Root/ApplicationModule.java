package twitter.com.example.gevik.twitter.Root;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gevik on 4/10/2018.
 */
@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }
}
