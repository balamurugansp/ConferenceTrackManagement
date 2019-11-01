package com.conference.management.vo;

import com.conference.management.configuration.Configuration;

public class Lunch extends Event {
    public Lunch() {
        super(Configuration.LUNCH_START_TIME, "Lunch", Configuration.LUNCH_DURATION_MINUTES);
    }
}

