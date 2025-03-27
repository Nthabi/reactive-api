package com.nthabi.reactiveapi.config;

import org.h2.tools.Server;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Configuration
@Profile({"test", "local"}) //@Profile("!prod")
public class H2Config {

    private final String WEB_PORT = "8082";
    private Server webServer;

    @EventListener(ApplicationStartedEvent.class)
    public void start() throws SQLException {
        String WEB_PORT = "8082";
        this.webServer = Server.createWebServer("-webPort", WEB_PORT).start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
    }


}
