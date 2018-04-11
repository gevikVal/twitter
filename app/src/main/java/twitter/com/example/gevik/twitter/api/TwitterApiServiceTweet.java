package twitter.com.example.gevik.twitter.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by gevik on 4/10/2018.
 */

public interface TwitterApiServiceTweet {
    @GET(ApiConstants.TWITTER_HASHTAG_SEARCH_CODE)
    void getTweetList(
            @Header("Authorization") String authorization,
            @Query("q") String hashtag,
            Call<TweetList> callback
    );
}
