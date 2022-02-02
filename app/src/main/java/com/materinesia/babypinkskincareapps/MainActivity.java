package com.materinesia.babypinkskincareapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.net.Uri;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import android.content.Intent;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://babypinkskincare.com/");
        webView.setWebViewClient(new myWebclient());
        webView.getSettings().setDomStorageEnabled(true);

    }

    // Untuk mengatasi problem ERROR_URL_SCHEME (bisa secara otomatis redirect ke aplikasi whatsapp ketika ada perintah dalam web)
    public class myWebclient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView wv, String url) {
            if (url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

                return true;
            }
            return false;
        }
    }

    //untuk menambahkan perintah memunculkan notifikasi ketika tombol back ditekan
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Yakin mau keluar nih Babylicious ?")
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setPositiveButton("Iya Beb", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Engga jadi deh", null)
                    .show();
        }
    }
}
