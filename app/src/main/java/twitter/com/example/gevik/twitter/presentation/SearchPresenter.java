package twitter.com.example.gevik.twitter.presentation;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by gevik on 4/10/2018.
 */

public class SearchPresenter implements SearchPresentationContract.Presenter {
    SearchPresentationContract.View view;
    @Inject
    public SearchPresenter(SearchPresentationContract.View view) {
        this.view = view;
    }

    @Override
    public void setView(SearchPresentationContract.View view) {

    }

    @Override
    public void searchButtonClicked() {
        Log.i("itIs","clicked");
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
}
