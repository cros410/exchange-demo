package com.example.bcpexchange.context.shared.domain;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
    void publish(final DomainEvent event);
}
