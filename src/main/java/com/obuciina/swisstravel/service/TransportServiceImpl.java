package com.obuciina.swisstravel.service;

import com.obuciina.swisstravel.exception.NotFoundException;
import com.obuciina.swisstravel.model.Duration;
import com.obuciina.swisstravel.model.Point;
import com.obuciina.swisstravel.model.Relation;
import com.obuciina.swisstravel.model.Station;
import com.obuciina.swisstravel.model.dto.ConnectionDTO;
import com.obuciina.swisstravel.model.dto.SwissResponseDTO;
import com.obuciina.swisstravel.util.DurationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {

    private final static Logger logger = LoggerFactory.getLogger(TransportServiceImpl.class);

    @Value("${swiss.base-url}")
    private String swissBaseUrl;
    private final DurationUtil durationUtil;
    private final RestTemplate restTemplate;

    public TransportServiceImpl(DurationUtil durationUtil, RestTemplate restTemplate) {
        this.durationUtil = durationUtil;
        this.restTemplate = restTemplate;
    }

    /**
     * {@inheritDoc}
     */
    public SwissResponseDTO findConnections(String start, String destination) {
        logger.info("Trying to find relation between {} and {}.", start, destination);
        String url = swissBaseUrl + "/connections?from={from}&to={to}";

        Map<String, String> uri = new HashMap<>();
        uri.put("from", start);
        uri.put("to", destination);

        ConnectionDTO connections = restTemplate.getForObject(url, ConnectionDTO.class, uri);
        if (connections == null) {
            logger.error("Unable to found connection for {} and {}", start, destination);
            throw new NotFoundException("Unable to found connection.");
        } else if (connections.connections().isEmpty()) {
            logger.error("Unable to found relation between {} and {}", start, destination);
            throw new NotFoundException("Unable to found relation between provided locations.");
        }

        List<String> durations = getDurations(connections);
        String foundStartStation = getStartStation(connections);
        String foundFinalStation = getDestinationStation(connections);
        logger.info("Relation found for {} and {}", foundStartStation, foundFinalStation);

        Duration avgDuration = durationUtil.getAverageDuration(durations);

        return new SwissResponseDTO(foundStartStation, foundFinalStation, avgDuration);
    }

    private static String getStartStation(ConnectionDTO connections) {
        Optional<String> station = connections.connections()
                .stream()
                .map(Relation::from)
                .map(Point::station)
                .map(Station::name)
                .findFirst();
        return station.map(String::trim).orElse("");
    }

    private static String getDestinationStation(ConnectionDTO connections) {
        Optional<String> station = connections.connections()
                .stream()
                .map(Relation::to)
                .map(Point::station)
                .map(Station::name)
                .findFirst();
        return station.map(String::trim).orElse("");
    }

    private static List<String> getDurations(ConnectionDTO connections) {
        return connections.connections()
                .stream()
                .map(Relation::duration)
                .toList();
    }
}


