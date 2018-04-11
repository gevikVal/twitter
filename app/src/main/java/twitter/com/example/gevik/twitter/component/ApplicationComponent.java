package twitter.com.example.gevik.twitter.component;



import dagger.Component;
import twitter.com.example.gevik.twitter.Root.ApplicationModule;
import twitter.com.example.gevik.twitter.SearchScreen.SearchActivity;
import twitter.com.example.gevik.twitter.TwitterApplication;
import twitter.com.example.gevik.twitter.module.AndroidModule;
import twitter.com.example.gevik.twitter.module.NetworkModule;
import twitter.com.example.gevik.twitter.module.SearchModule;
import twitter.com.example.gevik.twitter.scope.ApplicationScope;


/**
 * Created by gevik on 2/26/2018.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, AndroidModule.class, NetworkModule.class})
public interface ApplicationComponent {
    // Setup injection targets
    void inject(TwitterApplication target);


    SearchComponent createSubComponent(SearchModule searchModule);
}




