package com.example.halykbank.scheduler;

import com.example.halykbank.dto.RequestDto;
import com.example.halykbank.model.Message;
import com.example.halykbank.service.ClientService;
import com.example.halykbank.service.MessageService;
import com.hazelcast.cluster.Cluster;
import com.hazelcast.cluster.Member;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.scheduledexecutor.IScheduledExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class MessageSheduler{
    private static final Logger log = LoggerFactory.getLogger(MessageSheduler.class);
    @Autowired
    private IScheduledExecutorService myScheduledExecSvc;
    @Autowired
    private HazelcastInstance hazelcastInstance;
    private static BlockingQueue<RequestDto> messageQueue;
    private static MessageService messageService;
    private static ClientService clientService;
    @Autowired
    private void setMessageQueue(HazelcastInstance hazelcastInstance){
        messageQueue = hazelcastInstance.getQueue("messageQueue");
    }
    @Autowired
    private void setMessageService(MessageService messageService){
        this.messageService = messageService;
    }
    @Autowired
    private void setClientService(ClientService clientService){
        this.clientService = clientService;
    }
    @Scheduled(fixedDelay = 10000, initialDelay = 2000)
    void startJob() {
        Member oldestMember = getOldestMember();
        String leaderAddress = oldestMember.getSocketAddress().toString();
        String currentAddress = hazelcastInstance.getCluster().getLocalMember().getSocketAddress().toString();

        if (currentAddress.equals(leaderAddress)) {
            myScheduledExecSvc.scheduleOnMember(new ScheduleJob(), oldestMember,10, TimeUnit.SECONDS);
        }
    }

    private Member getOldestMember() {
        Cluster cluster = hazelcastInstance.getCluster();
        Member oldestMember = null;
        for (Member member : cluster.getMembers()) {
            if (oldestMember == null || member.getUuid().compareTo(oldestMember.getUuid()) < 0) {
                oldestMember = member;
            }
        }
        return oldestMember;
    }

    static class ScheduleJob implements Serializable, Runnable{
        private static final long serialVersionUID = 1L;

        @Override
        public void run() {
            log.info("Queue size: " + messageQueue.size());
            while (messageQueue.size() > 0) {
                RequestDto request = messageQueue.remove();
                Message message = messageService.save(Message.builder()
                                .client(clientService.findClientByLogin(request.getLogin()))
                                .bodyText(request.getBodyText())
                                .createdAt(new Date())
                        .build());

                log.info(message.toString());
            }
            log.info("end");
        }
    }
}
