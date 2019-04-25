package mvpdemo.yc.example.com.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mvpdemo.yc.example.com.library.listener.NetChangeObserver;
import mvpdemo.yc.example.com.library.type.NetType;
import mvpdemo.yc.example.com.library.utils.NetWorkUtils;

/**
 * @author by Yuchao on 2019/3/27.
 */

public class NetSateReceiver extends BroadcastReceiver {

    private NetType netType;
    private NetType oldNetType;
    private NetChangeObserver listener;

    public void setListener(NetChangeObserver listener) {
        this.listener = listener;
    }

    public NetSateReceiver() {
        this.netType = NetType.NONE;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)) {
            netType = NetWorkUtils.getNetType();
            //为了处理wifi接受到两次相同的网络状态的广播
            if (oldNetType != netType) {
                oldNetType = netType;
                if (NetWorkUtils.isNetWorkAvailable()) {
                    if (listener != null)
                        listener.onConnect(netType);
                } else {
                    if (listener != null) {
                        listener.onDisConnect();
                    }
                }
            }
        }

    }
}
