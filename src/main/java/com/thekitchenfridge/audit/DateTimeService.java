package com.thekitchenfridge.audit;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public interface DateTimeService {
    OffsetDateTime getCurrentDateAndTime();
}
