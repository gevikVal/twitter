package twitter.com.example.gevik.twitter.api.Repository.search;

import io.reactivex.Single;
import twitter.com.example.gevik.twitter.api.TweetList;
import twitter.com.example.gevik.twitter.api.TwitterTokenType;

/**
 * Created by gevik on 4/11/2018.
 */

public interface SearchDataSourceContract {
    interface getToken {
        Single<TwitterTokenType> getToken();

        Single<TweetList> getTweets(String searchTweet,String token);
    }
}
