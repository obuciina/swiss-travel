package com.obuciina.swisstravel.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DurationUtilTest {

    private DurationUtil durationUtil;

    @BeforeEach
    void setUp() {
        durationUtil = new DurationUtil();
    }

    @Test
    void testGetAverageDurationOne() {
        List<String> oneDuration = List.of("00d00:49:00");
        durationUtil.getAverageDuration(oneDuration);

        assertEquals("Average time is: 0 days, 0 hours, 49 minutes and 0 seconds.",
                durationUtil.getAverageDuration(oneDuration));
    }

    @Test
    void testGetAverageDurationMultiple() {
        List<String> oneDuration = Arrays.asList("00d00:49:00", "00d00:50:00", "00d00:30:00", "01d00:40:00");
        durationUtil.getAverageDuration(oneDuration);

        assertEquals("Average time is: 0 days, 6 hours, 42 minutes and 15 seconds.",
                durationUtil.getAverageDuration(oneDuration));
    }
}
