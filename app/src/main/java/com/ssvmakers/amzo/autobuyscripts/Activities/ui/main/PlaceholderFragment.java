package com.ssvmakers.amzo.autobuyscripts.Activities.ui.main;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ssvmakers.amzo.autobuyscripts.Activities.SearchActivity;
import com.ssvmakers.amzo.autobuyscripts.R;
import com.ssvmakers.amzo.autobuyscripts.Utils.CustomWebView;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private CustomWebView webView;
    private PageViewModel pageViewModel;
    private Bundle webViewBundle;

    public static PlaceholderFragment newInstance(int index, String data) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webViewBundle = new Bundle();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (webView != null && webViewBundle.isEmpty()) {
            webView.saveState(webViewBundle);
        }
    }

    public static boolean handleBackPressed(FragmentManager fm) {
        if (fm.getFragments() != null) {
            for (Fragment frag : fm.getFragments()) {
                if (frag != null && frag.isVisible() && frag instanceof PlaceholderFragment) {
                    if (((PlaceholderFragment) frag).onBackPressed()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean onBackPressed() {
        FragmentManager fm = getChildFragmentManager();
        if (handleBackPressed(fm)) {
            return true;
        } else if (getUserVisibleHint() && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else if (getUserVisibleHint() && fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
            return true;
        }
        return false;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String storeUrl = bundle.getString("storeUrl");
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        RelativeLayout loader_relative = root.findViewById(R.id.loader_relative);
        if (webView == null) {
            webView = root.findViewById(R.id.search_webview);
        }
        webView.setGestureDetector(new GestureDetector(new CustomeGestureDetector()));
        if (webViewBundle == null || webViewBundle.isEmpty()) {
            webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 9; SM-G960F Build/PPR1.180610.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.157 Mobile Safari/537.36");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    loader_relative.setVisibility(View.VISIBLE);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    loader_relative.setVisibility(View.GONE);
                }
            });
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    if (newProgress >= 40) {
                        loader_relative.setVisibility(View.GONE);
                    }
                    Log.d("progress", "--" + newProgress);
                }
            });
            webView.getSettings().setSaveFormData(false);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.setScrollbarFadingEnabled(false);
            webView.setVerticalScrollBarEnabled(true);
            webView.setHorizontalScrollBarEnabled(true);
            webView.requestFocusFromTouch();
            if (Build.VERSION.SDK_INT >= 19) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
            CookieManager.getInstance().setAcceptCookie(true);
            webView.loadUrl(storeUrl);
        } else {
            webView.restoreState(webViewBundle);
            webViewBundle.clear();
        }

        return root;
    }

    private class CustomeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1 == null || e2 == null) return false;
            if (e1.getPointerCount() > 1 || e2.getPointerCount() > 1) return false;
            else {
                try {
                    if (e1.getY() - e2.getY() > 20) {
                        // Hide Actionbar
                        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
                        webView.invalidate();
                        return false;
                    } else if (e2.getY() - e1.getY() > 20) {
                        // Show Actionbar
                        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
                        webView.invalidate();
                        return false;
                    }

                } catch (Exception e) {
                    webView.invalidate();
                }
                return false;
            }


        }
    }
}
