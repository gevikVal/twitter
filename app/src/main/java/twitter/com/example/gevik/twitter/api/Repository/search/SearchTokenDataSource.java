package twitter.com.example.gevik.twitter.api.Repository.search;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import twitter.com.example.gevik.twitter.Network.NetworkState;
import twitter.com.example.gevik.twitter.api.ApiConstants;
import twitter.com.example.gevik.twitter.api.TweetList;
import twitter.com.example.gevik.twitter.api.TwitterApiServiceToken;
import twitter.com.example.gevik.twitter.api.TwitterTokenType;

/**
 * Created by gevik on 4/11/2018.
 */

public class SearchTokenDataSource implements SearchDataSourceContract.getToken {
    NetworkState networkState;

    @Inject
    public SearchTokenDataSource(TwitterApiServiceToken twitterApiServiceToken, NetworkState networkState) {
        this.twitterApiServiceToken = twitterApiServiceToken;
        this.networkState = networkState;
    }

    private TwitterApiServiceToken twitterApiServiceToken;

    @Override
    public Single<TwitterTokenType> getToken() {
        Single<TwitterTokenType> remote = null;
        if (networkState.isConnectedOrConnecting()) {

            try {
                remote = twitterApiServiceToken.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return remote;


    }

    @Override
    public Single<TweetList> getTweets(String token, String searchTweet) {
        Single<TweetList> remote = null;
        remote = twitterApiServiceToken.getTweetList("Bearer " + token, searchTweet);
        return remote;

    }

    public void test() throws UnsupportedEncodingException {
        //  twitterApiServiceToken.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials"
//        try {
//            twitterApiServiceToken.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials", new Callback<TwitterTokenType>() {
//                @Override
//                public void onResponse(Call<TwitterTokenType> call, Response<TwitterTokenType> response) {
//                    Log.i("the code is",""+response.code());
//                }
//
//                @Override
//                public void onFailure(Call<TwitterTokenType> call, Throwable t) {
//
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return null;


        //  Single<TwitterTokenType> remote = twitterApiServiceToken.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials");
        //    return remote;

    }


    public static String getBase64String(String value) throws UnsupportedEncodingException {
        return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
    }


}
