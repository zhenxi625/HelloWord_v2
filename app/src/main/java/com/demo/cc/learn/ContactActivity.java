package com.demo.cc.learn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.demo.cc.appclick.R;
import com.demo.cc.model.Contact;
import com.demo.cc.service.ContactService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ChenXingLing on 2016/12/23.
 */
@SuppressLint("SetJavaScriptEnabled")
public class ContactActivity extends AppCompatActivity {

    private WebView webView;

    private ContactService contactService;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactlearn);

        webView = (WebView) this.findViewById(R.id.contact_webView);
        webView.loadUrl("file:///android_asset/index.html");
        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new JSObject(), "contact");

        contactService = new ContactService();
    }

    private final class JSObject {

        @SuppressWarnings("unused")
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
        }
    }
}
