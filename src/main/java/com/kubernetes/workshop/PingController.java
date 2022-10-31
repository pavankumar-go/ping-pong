package com.kubernetes.workshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String getPing() {
        try {
            long execTime = 0;
            String pingExecTime = System.getenv("PING_EXECUTION_TIME");

            if (pingExecTime != "") {
                execTime = Long.parseLong(pingExecTime);
            }

            Thread.sleep(execTime);
            return "pong";
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/color")
    public String getColor() {
        return System.getenv("COLOR");
    }
}
