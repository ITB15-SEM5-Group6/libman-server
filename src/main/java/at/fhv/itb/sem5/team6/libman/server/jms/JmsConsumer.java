package at.fhv.itb.sem5.team6.libman.server.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class JmsConsumer {

    private final Queue<String> messages = new LinkedList<>();

    @JmsListener(destination = "operators", containerFactory = "myFactory")
    public void receiveMessage(String message) {
        messages.offer(message);
    }

    public String getNextMessage() {
        return messages.poll();
    }
}
