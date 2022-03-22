package com.example.bcpexchange.context.shared.domain;

public abstract class DomainEvent {

    protected DomainEvent() {
    }

    public abstract String eventName();
}
