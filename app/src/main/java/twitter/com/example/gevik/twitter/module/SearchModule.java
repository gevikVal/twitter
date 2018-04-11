package twitter.com.example.gevik.twitter.module;

import dagger.Module;
import dagger.Provides;
import twitter.com.example.gevik.twitter.api.Repository.search.SearchDataSourceContract;
import twitter.com.example.gevik.twitter.api.Repository.search.SearchTokenDataSource;
import twitter.com.example.gevik.twitter.api.TwitterApiServiceToken;
import twitter.com.example.gevik.twitter.api.TwitterApiServiceTweet;
import twitter.com.example.gevik.twitter.presentation.SearchPresentationContract;
import twitter.com.example.gevik.twitter.presentation.SearchPresenter;
import twitter.com.example.gevik.twitter.ViewModel.ViewModel;

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
    public SearchDataSourceContract.getToken provideToken(TwitterApiServiceToken twitterApiServiceToken) {
        return new SearchTokenDataSource(twitterApiServiceToken);
    }


    @Provides
    public SearchPresentationContract.Presenter provideSearchPresenter(SearchDataSourceContract.getToken tokenDataSource) {
        return new SearchPresenter(view, tokenDataSource);
    }

    @Provides
    public SearchPresentationContract.ViewModel provideViewModel() {
        return new ViewModel();
    }
}
