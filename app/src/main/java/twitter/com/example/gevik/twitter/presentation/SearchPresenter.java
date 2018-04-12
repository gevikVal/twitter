package twitter.com.example.gevik.twitter.presentation;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import twitter.com.example.gevik.twitter.api.Repository.search.SearchDataSourceContract;
import twitter.com.example.gevik.twitter.api.TweetList;
import twitter.com.example.gevik.twitter.api.TwitterTokenType;

/**
 * Created by gevik on 4/10/2018.
 */

public class SearchPresenter implements SearchPresentationContract.Presenter, LifecycleObserver {
    SearchPresentationContract.View view;
    SearchDataSourceContract.getToken tokenDataSource;
    TweetList tweetList = new TweetList();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SearchPresenter(SearchPresentationContract.View view, SearchDataSourceContract.getToken tokenDataSource) {
        this.view = view;
        this.tokenDataSource = tokenDataSource;
    }

    @Override
    public void setView(SearchPresentationContract.View view) {
        this.view = view;
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void searchButtonClicked(String searchText) {
        Disposable disposable = tokenDataSource.getToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TwitterTokenType>() {
                    @Override
                    public void onSuccess(TwitterTokenType token) {
                        getTweets(token, searchText);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.showError("Error");
                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override
    public void getTweetList() {
        view.showTweets(this.tweetList);
    }

    private void getTweets(TwitterTokenType moviesDomainModel, String searchItem) {
        Disposable disposable = tokenDataSource.getTweets(moviesDomainModel.accessToken, searchItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TweetList>() {
                    @Override
                    public void onSuccess(TweetList tweetList) {
                        setTweetList(tweetList);
                        Log.i("called2", "" + tweetList.tweets.size());
                        view.showTweets(tweetList);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                         view.showError("Error");
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void setTweetList(TweetList tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }
    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

}
