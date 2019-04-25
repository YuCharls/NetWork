package mvpdemo.yc.example.com.library.listener;

import mvpdemo.yc.example.com.library.type.NetType;

/**
 * 接口监听
 * Created by Yuchao on 2019/3/27.
 */

public interface NetChangeObserver {


    void onConnect(NetType netType);

    void onDisConnect();


}
