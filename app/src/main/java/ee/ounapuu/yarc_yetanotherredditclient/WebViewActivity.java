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

        // TODO task 13: what is this? Explain.
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            webView.loadUrl("localhost");

        } else {
            webView.loadUrl(extras.getString("url"));        // TODO task 12:

        }
    }
}