package com.thekitchenfridge.audit;

import lombok.AllArgsConstructor;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.temporal.TemporalAccessor;
import java.util.GregorianCalendar;
import java.util.Optional;

@AllArgsConstructor
public class AuditDateTimeProvider implements DateTimeProvider {

    private final DateTimeService dateTimeService;

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(dateTimeService.getCurrentDateAndTime());
    }
}
