package twitter.com.example.gevik.twitter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import twitter.com.example.gevik.twitter.SearchScreen.SearchActivity;

public class MainActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.layout_login)
    LinearLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this, this);
        fadeIn(loginLayout);
    }

    private void fadeIn(LinearLayout layout) {       //fade in animation
        Animation fadeOut = new AlphaAnimation(0, 1);  // the 1, 0 here notifies that we want the opacity to go from opaque (1) to transparent (0)
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1800); // Start fading out after 500 milli seconds
        fadeOut.setDuration(2500); // Fadeout duration should be 1000 milli seconds
        layout.setAnimation(fadeOut);
        layout.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(() -> fadeOut(layout), 4100);
    }

    private void fadeOut(final LinearLayout layout) {  //fade out animation
        Intent intent = new Intent(this, SearchActivity.class);
        Animation fadeOut = new AlphaAnimation(1, 0);  // the 1, 0 here notifies that we want the opacity to go from opaque (1) to transparent (0)
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1900); // Start fading out after 500 milli seconds
        fadeOut.setDuration(4500); // Fadeout duration should be 1000 milli seconds
        layout.setAnimation(fadeOut);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(intent);
            finish();
        }, 6100);
    }

}
