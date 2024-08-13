package com.vellmond.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class VellmondClientStandaloneApplication implements ApplicationRunner {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VellmondClientStandaloneApplication.class);

    @Autowired
    private EurekaClient eurekaClient;
    public static void main(String[] args) {
        SpringApplication.run(VellmondClientStandaloneApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
       Application application= eurekaClient.getApplication("vellmond-dragonball");
       LOGGER.info("Application Name: {}", application.getName());
       List<InstanceInfo> instances = application.getInstances();
         for(InstanceInfo instanceInfo: instances){
              LOGGER.info("------Instance Id: {}, Host Name: {}, IP Address: {}, Port: {}, Status: {}",
                     instanceInfo.getInstanceId(),
                     instanceInfo.getHostName(),
                     instanceInfo.getIPAddr(),
                     instanceInfo.getPort(),
                     instanceInfo.getStatus());
        }


    }
}
