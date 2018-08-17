package ee.ounapuu.yarc_yetanotherredditclient;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // TODO task EASY: what is this "WebView" you speak of? Explain.
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // TODO task MODERATE: You need to find a way to pass an URL using the Intent.
            webView.loadUrl("this is not right");
        }
    }
}