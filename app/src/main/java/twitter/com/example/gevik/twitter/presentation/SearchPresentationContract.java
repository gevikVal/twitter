package twitter.com.example.gevik.twitter.presentation;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.view.ViewOutlineProvider;

import twitter.com.example.gevik.twitter.api.TweetList;

/**
 * Created by gevik on 4/10/2018.
 */

public interface SearchPresentationContract {
    interface View {
        void searchSuccessful();

        void showTweets(TweetList tweetList);

        void showError(String errorMessage);
    }

    interface Presenter {

        void setView(SearchPresentationContract.View view);

        void searchButtonClicked(String searchText);

        void getTweetList();


        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause();

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume();
    }

    interface ViewModel {
        void setPresenter(SearchPresentationContract.Presenter presenter);

        SearchPresentationContract.Presenter getPresenter();
    }


}
