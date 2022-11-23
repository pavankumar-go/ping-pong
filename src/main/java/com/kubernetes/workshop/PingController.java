package com.kubernetes.workshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    Environment env;

    @PostConstruct
    public void init()
    {
        System.out.println("PING_EXECUTION_TIME is set to " + env.getProperty("PING_EXECUTION_TIME"));
        System.out.println("COLOR is set to " + env.getProperty("COLOR"));
    }

    @GetMapping("/ping")
    public String getPing() {
        try {
            long execTime = 0;
            String pingExecTime = System.getenv("PING_EXECUTION_TIME");

            if (pingExecTime != null && !pingExecTime.equals(""))
            {
                execTime = Long.parseLong(pingExecTime);
                System.out.println("The method will simulate sleep for " + execTime + " seconds");
            }

            Thread.sleep(execTime * 1000);
            System.out.println("Responding...");
            return "pong";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/color")
    public String getColor() {
        System.out.println("Responding to /color ...");
        return System.getenv("COLOR");
    }
}
