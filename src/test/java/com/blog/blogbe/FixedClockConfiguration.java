package com.blog.blogbe;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.*;

@TestConfiguration
public class FixedClockConfiguration {

    @Primary
    @Bean
    public Clock fixedClock() {
        LocalDate localDate = LocalDate.parse("2025-01-01");
        LocalDateTime localDateTime = localDate.atStartOfDay();
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        return Clock.fixed(instant, ZoneId.of("UTC"));
    }
}
