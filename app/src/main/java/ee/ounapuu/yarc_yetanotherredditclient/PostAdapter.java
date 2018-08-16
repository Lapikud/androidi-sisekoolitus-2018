package ee.ounapuu.yarc_yetanotherredditclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ee.ounapuu.yarc_yetanotherredditclient.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> subredditPosts;
    private static final String TAG = "yarc";


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView thumbnail;

        public TextView postTitle;
        public TextView karmaCount;
        public TextView subreddit;

        //        TODO task 4b: remember that author field you re-added? Well, we need to somehow reach it to be able to fill it with useful text, ya know. Would be nice to know who posted that.
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
            // TODO task 6: something's not quite right here...
            thumbnail = v.findViewById(R.id.post_thumbnail);
            postTitle = v.findViewById(R.id.post_title);
            karmaCount = v.findViewById(R.id.subreddit);
            subreddit = v.findViewById(R.id.karma_count);
        }
    }

    public PostAdapter(List<Post> posts) {
        Log.d(TAG, "setting posts");
        subredditPosts = posts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        PostAdapter.ViewHolder vh = new PostAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final PostAdapter.ViewHolder holder, final int position) {
        // TODO task 7: we have set the title here, yes, but what about other elements in the card?
        Log.d(TAG, "onbindviewholder");
        Log.d(TAG, subredditPosts.get(position).getTitle());
        holder.postTitle.setText(subredditPosts.get(position).getTitle());
        Picasso.with(holder.thumbnail.getContext()).load(subredditPosts.get(position).getThumbnailURL()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = holder.thumbnail.getContext();
                Intent intent = new Intent(ctx, WebViewActivity.class);
                intent.putExtra("url", subredditPosts.get(position).getPostURL());
                ctx.startActivity(intent);
            }
        });


        holder.postTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO task 14: make it so that tapping the title opens the comments view. Hint: Intents and CommentsActivity.
                Context ctx = holder.postTitle.getContext();
                Intent intent = new Intent(ctx, WebViewActivity.class);
                intent.putExtra("url", subredditPosts.get(position).getCommentsURL());
                ctx.startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return subredditPosts.size();
    }

}