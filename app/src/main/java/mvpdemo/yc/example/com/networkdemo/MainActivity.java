package mvpdemo.yc.example.com.networkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import mvpdemo.yc.example.com.library.NetWorkManager;
import mvpdemo.yc.example.com.library.listener.NetChangeObserver;
import mvpdemo.yc.example.com.library.type.NetType;

public class MainActivity extends AppCompatActivity implements NetChangeObserver {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetWorkManager.getDefault().init(getApplication());
        NetWorkManager.getDefault().setListener(this);


    }


    @Override
    public void onConnect(NetType netType) {
        switch (netType) {
            case AUTO:
                Log.d(TAG, "onConnect: 有网了");
                break;
            case WIFI:
                Log.d(TAG, "onConnect: 有wifi了");
                break;
            case CMNET:
            case CMWAP:
                Log.d(TAG, "onConnect: 有4G了");
                break;
            case NONE:
                Log.d(TAG, "onConnect: 无网");
                break;


        }
    }

    @Override
    public void onDisConnect() {
        Log.d(TAG, "onConnect: 无网");
    }
}
 