package twitter.com.example.gevik.twitter.api;


import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface TwitterApiServiceToken {

    @FormUrlEncoded
    @POST("/oauth2/token")
    Single<TwitterTokenType> getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType

    );

   // public static final Retrofit retrofit = new Retrofit.Builder()
      //      .baseUrl("https://api.twitter.com")
        //    .addConverterFactory(GsonConverterFactory.create())
        //    .build();
   @GET(ApiConstants.TWITTER_HASHTAG_SEARCH_CODE)
   Single<TweetList> getTweetList(
           @Header("Authorization") String authorization,
           @Query("q") String hashtag

   );
}

