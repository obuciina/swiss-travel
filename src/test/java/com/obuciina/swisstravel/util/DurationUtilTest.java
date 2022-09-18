package com.obuciina.swisstravel.util;

import com.obuciina.swisstravel.model.Duration;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;
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
        durationUtil = new DurationUtilImpl();
    }

    @Test
    void testGetAverageDurationOne() {
        //given
        List<String> oneDuration = List.of("00d00:49:00");
        List<String> multipleDuration = Arrays.asList("00d00:49:00", "00d00:50:00", "00d00:30:00", "01d00:40:00");
        SwissResponseDTO expectedOne = new SwissResponseDTO("start","destination",
                new Duration(0, 0, 49, 0));
        SwissResponseDTO expectedMultiple = new SwissResponseDTO("start", "destination",
                new Duration(0, 6, 42, 15));

        //when
        durationUtil.getAverageDuration(oneDuration);
        durationUtil.getAverageDuration(multipleDuration);

        //then
        assertEquals(expectedOne.duration(), durationUtil.getAverageDuration(oneDuration));
        assertEquals(expectedMultiple.duration(), durationUtil.getAverageDuration(multipleDuration));
    }

}
