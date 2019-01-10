package io.remme.java.websocketevents;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RemmeEventsEntity {
    EVENTS("events"),
    CATCH_UP("catch_up");

    private String event;
}
