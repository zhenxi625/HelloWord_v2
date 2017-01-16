package com.demo.cc.learn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.demo.cc.appclick.R;
import com.demo.cc.model.Contact;
import com.demo.cc.service.ContactService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ChenXingLing on 2016/12/16.
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebViewLearn extends AppCompatActivity {

    private Handler mHandler = new Handler();

    private ContactService contactService;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewlearn);

        final WebView webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void clickOnAndroid() {
                System.out.println("clickOnAndroid被调用了....");
                /**
                 * 注意:这里调用webview的方法必须在同一个线程中完成,否则抛出异常:
                 * java.lang.RuntimeException: java.lang.Throwable: A WebView method was called on thread 'JavaBridge'. All WebView methods must be called on the same thread.
                 * (Expected Looper Looper (main, tid 1) {1d832dfa} called on Looper (JavaBridge, tid 263) {2a93b47f}, FYI main Looper is Looper (main, tid 1) {1d832dfa})
                 */
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:wave()");
                    }
                });
            }

            /*@JavascriptInterface
            public void showcontacts() {
                try {
                    List<Contact> contacts = contactService.getContacts();
                    JSONArray jsonArray = new JSONArray();
                    for (Contact c : contacts) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name", c.getName());
                        jsonObject.put("amount", c.getAmount());
                        jsonObject.put("phone", c.getPhone());

                        jsonArray.put(jsonObject);
                    }
                    String json = jsonArray.toString();
                    webView.loadUrl("javascript:show('" + json + "')");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/

            @JavascriptInterface
            public void showcontacts() {
                final String json = "[{\"name\":\"zxx\", \"amount\":\"9999999\", \"phone\":\"18600012345\"}]";
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:show('" + json + "')");
                    }
                });
            }
        }, "callByJs");
        webView.loadUrl("file:///android_asset/index2.html");

    }

}