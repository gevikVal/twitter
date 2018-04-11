package twitter.com.example.gevik.twitter.ViewModel;

import twitter.com.example.gevik.twitter.presentation.SearchPresentationContract;

/**
 * Created by gevik on 04/11/2018.
 */

public class ViewModel extends android.arch.lifecycle.ViewModel implements SearchPresentationContract.ViewModel {
    SearchPresentationContract.Presenter presenter;

    @Override
    public void setPresenter(SearchPresentationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public SearchPresentationContract.Presenter getPresenter() {
        return presenter;
    }
}
