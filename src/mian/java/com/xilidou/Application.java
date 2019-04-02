package com.xilidou;

import com.xilidou.verticle.SocksServer;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    private SocksServer netServer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);

    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(netServer);
    }

}
