package twitter.com.example.gevik.twitter.module;

import dagger.Module;
import dagger.Provides;
import twitter.com.example.gevik.twitter.presentation.SearchPresentationContract;
import twitter.com.example.gevik.twitter.presentation.SearchPresenter;

/**
 * Created by gevik on 4/10/2018.
 */
@Module
public class SearchModule {
    private SearchPresentationContract.View view;

    public SearchModule(SearchPresentationContract.View view) {
        this.view = view;
    }

    @Provides
    public SearchPresentationContract.Presenter  provideSearchPresenter(){
        return new SearchPresenter(view);
    }
}
