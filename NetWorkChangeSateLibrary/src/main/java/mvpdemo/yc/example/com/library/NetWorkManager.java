package mvpdemo.yc.example.com.library;

import android.app.Application;
import android.content.IntentFilter;

import mvpdemo.yc.example.com.library.listener.NetChangeObserver;

/**
 * Created by Yuchao on 2019/3/27.
 */

public class NetWorkManager {


    private static volatile NetWorkManager instance;
    private Application application;
    private NetSateReceiver receiver;

    private NetWorkManager() {
        receiver = new NetSateReceiver();
    }

    public static NetWorkManager getDefault() {
        if (instance == null) {
            synchronized (NetWorkManager.class) {
                if (instance == null) {
                    instance = new NetWorkManager();
                }
            }
        }
        return instance;
    }

    public void setListener(NetChangeObserver listener) {
        receiver.setListener(listener);
    }

    public void init(Application application) {
        if (application == null) {
            return;
        }
        this.application = application;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
        application.registerReceiver(receiver, intentFilter);


    }

    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("init()方法 未初始化");
        }

        return application;
    }


}
