package twitter.com.example.gevik.twitter.SearchScreen;

import android.app.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import twitter.com.example.gevik.twitter.BaseActivity;
import twitter.com.example.gevik.twitter.R;

import twitter.com.example.gevik.twitter.TwitterApplication;
import twitter.com.example.gevik.twitter.api.TweetList;
import twitter.com.example.gevik.twitter.component.SearchComponent;
import twitter.com.example.gevik.twitter.module.SearchModule;
import twitter.com.example.gevik.twitter.ViewModel.ViewModel;
import twitter.com.example.gevik.twitter.presentation.SearchPresentationContract;

public class SearchActivity extends BaseActivity implements SearchPresentationContract.View, TweetFragment.OnListFragmentInteractionListener {
    SearchComponent searchComponent;
    private Unbinder unbinder;
    TweetFragment tweetFragment;
    @BindView(R.id.search)
    Button searchBtn;
    @BindView(R.id.search_text)
    EditText searchText;
    @Inject
    SearchPresentationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysearch);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        unbinder = ButterKnife.bind(this, this);
        tweetFragment = (TweetFragment) getSupportFragmentManager().findFragmentById(R.id.tweetFragment);
        searchBtn.setBackgroundResource(R.drawable.btn_press);

        createSearchComponent().inject(this);
        if (searchText.getText().toString() != null) {
            searchBtn.setOnClickListener(view -> presenter.searchButtonClicked(searchText.getText().toString()));
        } else {
            Toast.makeText(this, "Please enter search Item", Toast.LENGTH_SHORT).show();
        }
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(presenter);
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
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListFragmentInteraction(TweetList item) {

    }
}
