package com.obuciina.swisstravel.util;

import com.obuciina.swisstravel.model.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DurationUtilImpl implements DurationUtil {

    private final static Logger logger = LoggerFactory.getLogger(DurationUtilImpl.class);

    /**
     * {@inheritDoc}
     */
    public Duration getAverageDuration(List<String> durations) {
        int seconds = timeToSeconds(durations);
        Duration avgDuration = mapSecondsToDuration(seconds / durations.size());
        logger.info("Average time duration is {}", avgDuration);
        return avgDuration;
    }

    /**
     * Going trought the String list and splitting each into days and times.
     * Going throw the time array and parsing String into Integer.
     * Calculating days, hours, minutes to seconds and make a sum.
     *
     * @param durations the list of found durations
     * @return the sum of durations seconds
     */
    private int timeToSeconds(List<String> durations) {
        int seconds = 0;
        for (String duration : durations) {
            String[] days = duration.split("d");
            String[] time = days[1].split(":");
            seconds += Integer.parseInt(days[0]) * 86400;
            seconds += ((time.length >= 1) ? Integer.parseInt(time[0]) : 0) * 3600;
            seconds += ((time.length >= 2) ? Integer.parseInt(time[1]) : 0) * 60;
            seconds += ((time.length >= 3) ? Integer.parseInt(time[2]) : 0);
        }
        return seconds;
    }

    /**
     * Mapping seconds to Duration object.
     *
     * @param seconds the number of seconds
     * @return the time duration
     * @see Duration
     */
    private Duration mapSecondsToDuration(int seconds) {
        long day = TimeUnit.SECONDS.toDays(seconds);
        long hour = (TimeUnit.SECONDS.toHours(seconds) - (day * 24L));
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        return new Duration(day, hour, minute, second);
    }
}
