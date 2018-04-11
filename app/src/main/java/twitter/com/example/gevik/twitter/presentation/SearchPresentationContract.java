package twitter.com.example.gevik.twitter.presentation;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Created by gevik on 4/10/2018.
 */

public interface SearchPresentationContract {
    interface View {
        void searchSuccessful();
    }

    interface Presenter {

        void setView(SearchPresentationContract.View view);

        void searchButtonClicked();


        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause();

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume();
    }


}
