package me.chaozhouzhang.socketclientforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_send_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,"Client：Connecting...");
                        try {
                            Socket socket = new Socket("192.168.3.41",8080);

                            try{
                                String msg = "Message from Android.";
                                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                                out.println(msg);

                            }catch (Exception e){
                                Log.e(TAG,"Client：Error："+e.getMessage());

                            }finally {
                                socket.close();
                                Log.e(TAG,"Client：Close.");

                            }
                        } catch (IOException e) {

                            Log.e(TAG,"Client：Error："+e.getMessage());
                        }
                    }
                }).start();

            }
        });
    }
}
