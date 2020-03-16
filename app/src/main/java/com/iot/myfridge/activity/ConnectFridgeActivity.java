package com.iot.myfridge.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;
import com.iot.myfridge.R;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import cz.msebera.android.httpclient.Header;

public class ConnectFridgeActivity extends AppCompatActivity {

    private EditText urlText;
    private Button conntectButton;
    String urlPath = new String();
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    private static final int REQUEST_AUDIO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_fridge_view);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                // BEGIN_INCLUDE(camera_permission_request)
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.RECORD_AUDIO)) {
                    showMessageOKCancel("You need to allow access to Contacts", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO);
                        }
                    });
                } else {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_AUDIO);
                }
            }
        }
        urlText =(EditText)findViewById(R.id.fridge_id);
        conntectButton = (Button)findViewById(R.id.connect_button);

    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void sendGet(String path, String msg) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(path + msg, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String resp = new String(bytes);
                resp = "Connection setup: " + resp;
                Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Cannot not reach server: " + urlPath, Toast.LENGTH_LONG).show();
            }
        });
    }
}

