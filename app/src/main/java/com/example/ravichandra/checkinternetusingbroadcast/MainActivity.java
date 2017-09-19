package com.example.ravichandra.checkinternetusingbroadcast;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements ConnectivityReceiver.ConnectivityReceiverListener{

    Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheck = (Button) findViewById(R.id.btn_check);

        checkConnections();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manually checking internet connection ....
                checkConnections();
            }
        });
    }



    private void checkConnections() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        checkMessage(isConnected);
    }

    private void checkMessage(boolean isConnected) {
        String message;

        if (isConnected) {
            message = "Good! Connected to Internet";

        } else {
            message = "Sorry! Not connected to internet";

        }
Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(MainActivity.this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        checkMessage(isConnected);
    }

}
