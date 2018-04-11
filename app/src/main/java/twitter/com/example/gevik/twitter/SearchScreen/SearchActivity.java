package twitter.com.example.gevik.twitter.SearchScreen;

import android.app.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import twitter.com.example.gevik.twitter.R;
import twitter.com.example.gevik.twitter.SearchScreen.dummy.DummyContent;
import twitter.com.example.gevik.twitter.TwitterApplication;
import twitter.com.example.gevik.twitter.api.TweetList;
import twitter.com.example.gevik.twitter.component.SearchComponent;
import twitter.com.example.gevik.twitter.module.SearchModule;
import twitter.com.example.gevik.twitter.ViewModel.ViewModel;
import twitter.com.example.gevik.twitter.presentation.SearchPresentationContract;

public class SearchActivity extends AppCompatActivity implements SearchPresentationContract.View, TweetFragment.OnListFragmentInteractionListener {
    SearchComponent searchComponent;
    private Unbinder unbinder;
    TweetFragment tweetFragment;
    @BindView(R.id.search)
    Button searchBtn;
    @Inject
    SearchPresentationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysearch);
        unbinder = ButterKnife.bind(this, this);
        tweetFragment = (TweetFragment) getSupportFragmentManager().findFragmentById(R.id.tweetFragment);
        searchBtn.setBackgroundResource(R.drawable.btn_press);

        createSearchComponent().inject(this);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.searchButtonClicked();
            }
        });
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(presenter);
            Log.i("nullIs", "nullIs");
        }
        presenter = viewModel.getPresenter();
        presenter.setView(this);
        presenter.getTweetList();


    }

    SearchComponent createSearchComponent() {
        searchComponent = ((TwitterApplication) this.getApplication())
                .getApplicationComponent()
                .createSubComponent(new SearchModule(this));
        return searchComponent;

    }

    @Override
    public void searchSuccessful() {

    }

    @Override
    public void showTweets(TweetList tweetList) {
        tweetFragment.updateAdapter(tweetList);
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
