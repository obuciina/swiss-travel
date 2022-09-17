package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.exception.NotFoundException;
import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import com.obuciina.swisstravel.model.dto.DurationDTO;
import com.obuciina.swisstravel.model.dto.RelationDTO;
import com.obuciina.swisstravel.util.DurationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransportService {

    @Value("${swiss.base-url}")
    private String swissBaseUrl;
    private final DurationUtil durationUtil;

    public TransportService(DurationUtil durationUtil) {
        this.durationUtil = durationUtil;
    }

    public String findConnections(RelationDTO relationDTO) {
        String url = swissBaseUrl + "/connections?from={from}&to={to}";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> uri = new HashMap<>();
        uri.put("from", relationDTO.start());
        uri.put("to", relationDTO.destination());

        ConnectionDTO connections = restTemplate.getForObject(url, ConnectionDTO.class, uri);
        if (connections == null) {
            throw new NotFoundException("Unable to found relation between two places.");
        }

        List<String> durations = connections.connections()
                .stream()
                .map(DurationDTO::duration)
                .toList();

        return durationUtil.getAverageDuration(durations);
    }

}
