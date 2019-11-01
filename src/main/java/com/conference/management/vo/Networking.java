package com.conference.management.vo;

import com.conference.management.configuration.Configuration;

public class Networking extends Event {

    public Networking () {
        super(Configuration.NETWORKING_START_TIME, "Networking Event", 0);
    }
}
