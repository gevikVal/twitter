package twitter.com.example.gevik.twitter.presentation;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import twitter.com.example.gevik.twitter.api.Repository.search.SearchDataSourceContract;
import twitter.com.example.gevik.twitter.api.TweetList;
import twitter.com.example.gevik.twitter.api.TwitterTokenType;

/**
 * Created by gevik on 4/10/2018.
 */

public class SearchPresenter implements SearchPresentationContract.Presenter {
    SearchPresentationContract.View view;
    SearchDataSourceContract.getToken tokenDataSource;

    public SearchPresenter(SearchPresentationContract.View view, SearchDataSourceContract.getToken tokenDataSource) {
        this.view = view;
        this.tokenDataSource = tokenDataSource;
    }

    @Override
    public void setView(SearchPresentationContract.View view) {

    }

    @Override
    public void searchButtonClicked() {
        Disposable disposable = tokenDataSource.getToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TwitterTokenType>() {
                    @Override
                    public void onSuccess(TwitterTokenType moviesDomainModel) {
                             getTweets(moviesDomainModel);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });
    }

    private void getTweets(TwitterTokenType moviesDomainModel) {
        Disposable disposable = tokenDataSource.getTweets(moviesDomainModel.accessToken,"IBM")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TweetList>() {
                    @Override
                    public void onSuccess(TweetList moviesDomainModel) {
                     
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
}
