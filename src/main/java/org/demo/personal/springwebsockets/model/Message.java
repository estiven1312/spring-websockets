package org.demo.personal.springwebsockets.model;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String text;
}
