package com.example.halykbank;

import com.example.halykbank.dto.RequestDto;
import com.example.halykbank.model.Message;
import com.example.halykbank.scheduler.MessageSheduler;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.scheduledexecutor.IScheduledExecutorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class HalykBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(HalykBankApplication.class, args);
    }

    @Bean
    public Config hazelCastConfig() {
        return new Config().setManagementCenterConfig(
                new ManagementCenterConfig().setConsoleEnabled(true));

    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
        return Hazelcast.newHazelcastInstance(hazelCastConfig);
    }

    @Bean
    public BlockingQueue<RequestDto> messageQueue(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getQueue("messageQueue");
    }

    @Bean
    public IScheduledExecutorService myScheduledExecSvc(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getScheduledExecutorService("myScheduledExecSvc");
    }

}
