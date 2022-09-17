package com.obuciina.swisstravel.util;

import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import org.springframework.stereotype.Service;

@Service
public class DurationUtil {
    public String calculateAverageTime(ConnectionDTO connections) {
        return connections.getConnections().get(0).getDuration();
    }
}
