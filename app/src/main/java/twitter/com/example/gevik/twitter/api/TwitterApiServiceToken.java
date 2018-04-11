package twitter.com.example.gevik.twitter.api;


import retrofit2.Call;
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
    @POST("https://api.twitter.com/oauth2/token")
    void getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            Call<TwitterTokenType> response
    );

   // public static final Retrofit retrofit = new Retrofit.Builder()
      //      .baseUrl("https://api.twitter.com")
        //    .addConverterFactory(GsonConverterFactory.create())
        //    .build();
   @GET(ApiConstants.TWITTER_HASHTAG_SEARCH_CODE)
   void getTweetList(
           @Header("Authorization") String authorization,
           @Query("q") String hashtag,
           Call<TweetList> callback
   );
}

