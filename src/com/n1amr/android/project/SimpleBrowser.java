package com.n1amr.android.project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {
    WebView mWebView;
    EditText etURL;
    Button bGo, bBack, bForward, bRefresh, bClear;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.simplebrowser);

	bGo = (Button) findViewById(R.id.bGo);
	bBack = (Button) findViewById(R.id.bBack);
	bForward = (Button) findViewById(R.id.bForward);
	bRefresh = (Button) findViewById(R.id.bRefresh);
	bClear = (Button) findViewById(R.id.bClear);

	bGo.setOnClickListener(this);
	bBack.setOnClickListener(this);
	bForward.setOnClickListener(this);
	bRefresh.setOnClickListener(this);
	bClear.setOnClickListener(this);

	etURL = (EditText) findViewById(R.id.etURL);
	etURL.setText("http://www.google.com");

	mWebView = (WebView) findViewById(R.id.wvBrowser);
	mWebView.getSettings().setJavaScriptEnabled(true);
	mWebView.getSettings().setLoadWithOverviewMode(true);
	mWebView.getSettings().setUseWideViewPort(true);
	mWebView.setWebViewClient(new WebViewClient());
	mWebView.loadUrl("http://en.wikipedia.org/wiki/A");
    }

    @Override
    public void onClick(View v) {
	switch (v.getId()) {
	    case R.id.bGo:
		String sURL = etURL.getText().toString();
		mWebView.loadUrl(sURL);
		mWebView.setWebViewClient(new WebViewClient());

		// Hide Keyboard
		InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mInputMethodManager.hideSoftInputFromWindow(
			etURL.getWindowToken(), 0);
		//
		break;
	    case R.id.bBack:
		if (mWebView.canGoBack())
		    mWebView.goBack();
		break;
	    case R.id.bForward:
		if (mWebView.canGoForward())
		    mWebView.goForward();
		break;
	    case R.id.bRefresh:
		mWebView.reload();
		break;
	    case R.id.bClear:
		mWebView.clearHistory();
		break;

	    default:
		break;
	}
    }

}
