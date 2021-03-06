package cn.imadc.application.base.netty.remoting;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-02
 */
public interface RemotingService {

    void start();

    void shutdown();

    void registerRPCHook(RPCHook rpcHook);
}
