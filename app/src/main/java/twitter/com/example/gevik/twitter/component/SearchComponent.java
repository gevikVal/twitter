package twitter.com.example.gevik.twitter.component;

import dagger.Subcomponent;
import twitter.com.example.gevik.twitter.SearchScreen.SearchActivity;
import twitter.com.example.gevik.twitter.module.SearchModule;
import twitter.com.example.gevik.twitter.scope.PresentationScope;

/**
 * Created by gevik on 4/10/2018.
 */
@PresentationScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchComponent {
    void inject (SearchActivity target);
}
