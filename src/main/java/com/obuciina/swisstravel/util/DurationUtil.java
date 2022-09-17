package com.obuciina.swisstravel.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DurationUtil {


    public String getAverageDuration(List<String> durations) {
        int seconds = timeToSeconds(durations);
        return secondsToString(seconds / durations.size());
    }

    public int timeToSeconds(List<String> durations) {
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

    private String secondsToString(int seconds) {
        long day = TimeUnit.SECONDS.toDays(seconds);
        long hours = (TimeUnit.SECONDS.toHours(seconds) - (day * 24L));
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        return ("Average time is: " + day + " days, " + hours + " hours, " + minute + " minutes and " + second + " seconds.");
    }
}
