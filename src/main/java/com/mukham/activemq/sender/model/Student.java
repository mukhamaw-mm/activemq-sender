package com.mukham.activemq.sender.model;

import lombok.Data;

@Data
public class Student {
    Long id; //no need to put id in postman, it's auto generate in db.
    String name;
    String address;
    String phone;
}
