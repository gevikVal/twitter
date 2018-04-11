package twitter.com.example.gevik.twitter.SearchScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import twitter.com.example.gevik.twitter.R;
import twitter.com.example.gevik.twitter.SearchScreen.TweetFragment.OnListFragmentInteractionListener;
import twitter.com.example.gevik.twitter.SearchScreen.dummy.DummyContent.DummyItem;
import twitter.com.example.gevik.twitter.api.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTweetRecyclerViewAdapter extends RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder> {

    private final List<Tweet> tweetList = new ArrayList<>();
    Context context;

    private final OnListFragmentInteractionListener mListener;

    public MyTweetRecyclerViewAdapter(OnListFragmentInteractionListener listener, Context context) {

        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //  holder.mItem = tweetList.get(position);
        //holder.mIdView.setText(tweetList.get(position).id);
        //  holder.mContentView.setText(tweetList.get(position).content);

        holder.textTweet.setText(tweetList.get(position).text);
        holder.textUser.setText(tweetList.get(position).user.name);
        Picasso.with(context).load(tweetList.get(position).user.profileImageUrl).into(holder.imageLogo);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    // mListener.onListFragmentInteraction(holder.mView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView textTweet;
        TextView textUser;
        ImageView imageLogo;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textTweet = (TextView) view.findViewById(R.id.text_tweet);
            textUser = (TextView) view.findViewById(R.id.text_user);
            imageLogo = (ImageView) view.findViewById(R.id.image_user_logo);
        }


    }

    public void swapData(List<Tweet> list) {
        tweetList.clear();
        if (list != null) {
            tweetList.addAll(list);
        }
        notifyDataSetChanged();
    }
}
