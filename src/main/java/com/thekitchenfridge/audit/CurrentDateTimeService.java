package com.thekitchenfridge.audit;

import java.time.OffsetDateTime;

public class CurrentDateTimeService implements DateTimeService {
    @Override
    public OffsetDateTime getCurrentDateAndTime() {
        return OffsetDateTime.now();
    }
}
