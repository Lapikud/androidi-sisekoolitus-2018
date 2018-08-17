package ee.ounapuu.yarc_yetanotherredditclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import ee.ounapuu.yarc_yetanotherredditclient.model.Post;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "yarc";

    private static final String BASE_URL = "https://reddit.com";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Post> posts;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initSearchView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPosts(BASE_URL + "/.json"); // default front page posts
    }

    private void initRecyclerView() {

        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        posts = new ArrayList<>();

        // TODO task EASY: remove me once you have solved all CRITICAL tasks and everything works.
        Post p = new Post();
        p.setTitle("I am a placeholder. Hello!");
        posts.add(p);
        posts.add(p);
        posts.add(p);
        posts.add(p);

        mAdapter = new PostAdapter(posts);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initSearchView() {
        searchView = findViewById(R.id.subredditSearchbar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO task CRITICAL: remember what we discussed about reddit and the way to access the Reddit API?
                getPosts(BASE_URL);
                return true;
            }
        });
    }

    // TODO task BONUS: instead of all this manual work, try using a Reddit API wrapper.
    void getPosts(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        preparePosts(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);
    }

    void preparePosts(String response) {
        try {
            JSONObject json = (JSONObject) new JSONTokener(response).nextValue();

            // TODO task CRITICAL: well this can't be right. Look into the JSON structure with a browser/somne other tool and fix this so we get an array of the posts here.
            JSONArray postArray = json.getJSONObject("hurr").getJSONArray("durr");

            List<Post> newPosts = new ArrayList<>();
            for (int i = 0; i < postArray.length(); i++) {
                JSONObject row = postArray.getJSONObject(i).getJSONObject("data");

                // TODO task EASY: I feel like some information is missing here. Fill all of the relevant information.
                Post post = new Post();
                post.setTitle(row.getString("title"));
                post.setThumbnailURL(row.getString("thumbnail"));

                newPosts.add(post);
            }

            // TODO task EASY: explain what this code block does as a neat comment.
            posts.clear();
            posts.addAll(newPosts);
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
