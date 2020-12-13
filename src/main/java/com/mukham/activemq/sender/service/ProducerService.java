package com.mukham.activemq.sender.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukham.activemq.sender.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

/**
 * @Author Mu Kham Aw.
 * @Description this is producer to send data into queue.
 */

@RestController
public class ProducerService {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    Queue queue;

    @PostMapping("/student")
    public String publish(@RequestBody Student student) {

        System.out.println("Starting producer");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = "";
        try {
            //convert java object into json string
            jsonData = objectMapper.writeValueAsString(student);
            System.out.println("Sending into queue: " + jsonData);

            //send message into queue by using JMS Template.
            jmsTemplate.convertAndSend(queue, jsonData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Published successfully";

    }
}
