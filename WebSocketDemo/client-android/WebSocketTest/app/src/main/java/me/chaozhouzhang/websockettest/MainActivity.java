package me.chaozhouzhang.websockettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class MainActivity extends AppCompatActivity {
    private static final String WS_URL = "ws://192.168.3.41:8080/chatServer";
    WebSocketConnection webSocketConnection = new WebSocketConnection();
    @BindView(R.id.edt_user)
    EditText edtUser;
    @BindView(R.id.tv_print)
    TextView tvPrint;
    @BindView(R.id.edt_to)
    EditText edtTo;
    @BindView(R.id.edt_msg)
    EditText edtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @OnClick({R.id.btn_connect, R.id.btn_send, R.id.btn_disconnect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                connect();
                break;
            case R.id.btn_send:
                talk();
                break;
            case R.id.btn_disconnect:
                disconnect();
                break;
        }
    }

    /**
     * 进行连接
     */
    private void connect() {
        final String user = edtUser.getText().toString();
        if (TextUtils.isEmpty(user)) {
            print("请输入你的昵称！");
            return;
        }
        try {
            webSocketConnection.connect(WS_URL, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    super.onOpen();
                    print("你已连接到服务端！");
                    connected(user);
                }

                @Override
                public void onBinaryMessage(byte[] payload) {
                    super.onBinaryMessage(payload);
                    print(payload.toString());
                }

                @Override
                public void onRawTextMessage(byte[] payload) {
                    super.onRawTextMessage(payload);
                    print(payload.toString());
                }

                @Override
                public void onTextMessage(String payload) {
                    super.onTextMessage(payload);
                    print(payload.toString());
                }

                @Override
                public void onClose(int code, String reason) {
                    super.onClose(code, reason);
                    print("已断开连接，代码：" + code + "，原因是：" + reason + "！");
                }
            });
        } catch (WebSocketException e) {
            print("异常：" + e.getMessage() + "！");
        }
    }

    /**
     * 已连接
     *
     * @param user
     */
    private void connected(String user) {
        send(user);
    }

    /**
     * 聊天：单聊/群聊
     */
    private void talk() {
        String to = edtTo.getText().toString();
        if (TextUtils.isEmpty(to))
            to = "all";
        String msg = edtMsg.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            print("请输入要发送的消息！");
            return;
        }
        send(to + "@" + msg);
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    private void send(String msg) {
        if (webSocketConnection.isConnected()) {
            print(msg);
            webSocketConnection.sendTextMessage(msg);
        } else {
            print("尚未连接！");
        }
    }

    /**
     * 断开连接
     */
    private void disconnect() {
        webSocketConnection.disconnect();
        print("断开连接！");
    }

    /**
     * 打印日志到页面
     *
     * @param msg
     */
    private void print(String msg) {
        tvPrint.append(msg + "\n");
    }
}
