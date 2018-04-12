package twitter.com.example.gevik.twitter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import twitter.com.example.gevik.twitter.api.Repository.search.SearchDataSourceContract;
import twitter.com.example.gevik.twitter.presentation.SearchPresentationContract;
import twitter.com.example.gevik.twitter.presentation.SearchPresenter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by gevik on 4/11/2018.
 */

public class SearchPresenterTest {
    @Mock
    SearchPresentationContract.View view;
    @Mock
    SearchDataSourceContract.getToken tokenDataSource;




    SearchPresenter searchPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        searchPresenter = new SearchPresenter(view, tokenDataSource);
        searchPresenter.setView(view);
    }

    @Test
    public void getTweet(){
        tokenDataSource.getTweets("test","test");
        verify(tokenDataSource, times(1)).getTweets("test","test");
    }
    @Test
    public void searchButtonClicked() {
        searchPresenter.searchButtonClicked("test");
        verify(tokenDataSource, times(1)).getToken();
    }

}
