package com.example.SmartFarmingMonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SmartFarmingMonitorApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SmartFarmingMonitorApplication.class);

        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        Map<String, Object> props = new HashMap<>();
        props.put("server.port", port);
        app.setDefaultProperties(props);

        app.run(args);

		// not sure if this is necessary. render was giving whitelabel at first but i wasnt adding /dashboard to the url. not sure if that or this fixed it.
    }
}
