package twitter.com.example.gevik.twitter.SearchScreen;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import twitter.com.example.gevik.twitter.R;
import twitter.com.example.gevik.twitter.TwitterApplication;
import twitter.com.example.gevik.twitter.component.SearchComponent;
import twitter.com.example.gevik.twitter.module.SearchModule;
import twitter.com.example.gevik.twitter.presentation.SearchPresentationContract;

public class SearchActivity extends Activity implements SearchPresentationContract.View {
    SearchComponent searchComponent;
    private Unbinder unbinder;
    @BindView(R.id.search)
    Button searchBtn;
    @Inject
    SearchPresentationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysearch);
        unbinder = ButterKnife.bind(this, this);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.searchButtonClicked();
            }
        });
        createSearchComponent().inject(this);
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
}
