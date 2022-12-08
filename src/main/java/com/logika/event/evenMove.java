package com.logika.event;

public class evenMove {

    private final eventTipe eventTipe;
    private final eventSource eventSource;
    public evenMove(eventTipe eventTipe, eventSource eventSource){
        this.eventSource = eventSource;
        this.eventTipe = eventTipe;
    }

    public eventTipe getEventTipe() {
        return eventTipe;
    }

    public eventSource getEventSource() {
        return eventSource;
    }
}
