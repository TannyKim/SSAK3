package org.ssak3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ssack3Application {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        System.out.println("Hello jenkins");
        SpringApplication.run(Ssack3Application.class, args);
    }
}