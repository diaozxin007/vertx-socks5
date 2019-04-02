package com.xilidou.handler;

import com.xilidou.crypt.Crypt;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProxyHandler implements Handler<Buffer> {

    @Autowired
    private Crypt crypt;

    @Override
    public void handle(Buffer event) {
        System.out.println(event.toString());

        byte[] bytes = event.getBytes();

        String decrypt = crypt.decrypt(bytes);

        System.out.println( decrypt);
    }
}
