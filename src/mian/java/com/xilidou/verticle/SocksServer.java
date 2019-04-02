package com.xilidou.verticle;

import com.xilidou.handler.ProxyHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zhengxin
 */
@Component
public class SocksServer extends AbstractVerticle {

    @Autowired
    private ProxyHandler proxyHandler;

    @Override
    public void start() throws Exception {
        super.start();

        NetServer netServer = vertx.createNetServer();
        netServer.connectHandler(t -> t.handler(proxyHandler));

        netServer.listen(19000);

    }
}
