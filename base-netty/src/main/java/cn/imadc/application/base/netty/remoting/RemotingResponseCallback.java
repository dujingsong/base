package cn.imadc.application.base.netty.remoting;

import org.apache.rocketmq.remoting.protocol.RemotingCommand;

public interface RemotingResponseCallback {
    void callback(RemotingCommand response);
}
