package demojms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import demojms.model.NotificationMessage;



@RestController
public class TestMassageController {
    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/send")
    private void send() {
        NotificationMessage notif = new NotificationMessage();
        notif.setMessage("Assalamualaikum");
        jmsTemplate.convertAndSend("x-ians", notif);
    }  
    
    //Exercise Day 4
    //active Mq
    @GetMapping("/ping")
    private void ping() {
        NotificationMessage notif = new NotificationMessage();
        notif.setMessage("Ping");
        jmsTemplate.convertAndSend("ping-request", notif);
    }  
    @JmsListener(destination = "pong-request", containerFactory = "myFactory")
    public void receiveMessagePong(NotificationMessage notif) {
      System.out.println("Received <"+ notif.getMessage() + ">");

    // @JmsListener(destination = "ping-request", containerFactory = "myFactory")
    // public void receiveMessagePong(NotificationMessage notifPong) {
    //   System.out.println("Received <"+ notifPong.getMessage() + ">");
    }
}
