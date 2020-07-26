package com.sakinramazan.todoassistant.todoservice.queue;

public interface MessagePublisher {
    void publish(final String message);
}
