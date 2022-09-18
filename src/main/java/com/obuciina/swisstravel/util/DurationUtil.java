package com.obuciina.swisstravel.util;

import com.obuciina.swisstravel.model.Duration;

import java.util.List;

public interface DurationUtil {

    /**
     * Returns average time duration from provided duration list.
     *
     * @param  durations  a list of durations
     * @return      the average time duration
     * @see         Duration
     */
    Duration getAverageDuration(List<String> durations);
}
